package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.*;
import RayCorp.Ryoplan.Mapper.DayMapper;
import RayCorp.Ryoplan.Model.Activities;
import RayCorp.Ryoplan.Model.Day;
import RayCorp.Ryoplan.Model.Plan;
import RayCorp.Ryoplan.Repositories.DayRepository;
import RayCorp.Ryoplan.Repositories.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DayServiceImpl implements DayService{

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private DayRepository dayRepository;

    @Override
    public DayDTO createDay(DayDTO dto) {

        // ambil plan nya
        Plan plan = planRepository.findById(dto.getPlan().getPlan_id())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Day new_day = new Day();
        new_day.setPlan(plan);

        new_day.setDayTitle(dto.getDay_title());
        new_day.setDescription(dto.getDescription());
        new_day.setBiayaHotel(dto.getBiaya_hotel());

        // untuk counter hari + 1
        new_day.setDayCounter(plan.getAvailableCounter());
        plan.setAvailableCounter(plan.getAvailableCounter()+1);

        if (dto.getList_aktivitas() != null) {
            dto.getList_aktivitas().forEach(aktivitasDto -> {
                Activities a = new Activities();
                a.setActivityName(aktivitasDto.getActivity_name());
                a.setBudget(aktivitasDto.getBudget());
                a.setJamMulai(aktivitasDto.getJam_mulai());
                a.setJamSelesai(aktivitasDto.getJam_selesai());
                new_day.addAktivitas(a); // ini set a.setDay(this) + add ke list
            });
        }

        plan.getListDay().add(new_day);
        Day savedDay = dayRepository.save(new_day);
        return DayMapper.toDayDTO(savedDay);

    }

    @Override
    public DayDTO updateDay(Long day_id,DayDTO dto) {
        Day day = dayRepository.findById(day_id).orElseThrow(()->new EntityNotFoundException("Day with id: "+day_id+" not found"));
        Plan plan = planRepository.findById(dto.getPlan().getPlan_id()).orElseThrow(()->new EntityNotFoundException("Plan with id: "+dto.getPlan().getPlan_id()+" not found" ));
        day.setDayTitle(dto.getDay_title());
        day.setDescription(dto.getDescription());
        day.setPlan(plan);
        day.setBiayaHotel(dto.getBiaya_hotel());

        if (dto.getList_aktivitas() != null) {
            dto.getList_aktivitas().forEach(aktivitasDto -> {
                Activities a = new Activities();
                a.setActivityName(aktivitasDto.getActivity_name());
                a.setBudget(aktivitasDto.getBudget());
                a.setJamMulai(aktivitasDto.getJam_mulai());
                a.setJamSelesai(aktivitasDto.getJam_selesai());
                day.addAktivitas(a); // ini set a.setDay(this) + add ke list
            });
        }

        Day saved_day = dayRepository.save(day);
        return DayMapper.toDayDTO(saved_day);
    }

    @Override
    public List<DayDTO> listDayByPlan(Long plan_id) {
        List<Day> listDay = dayRepository.findAllByPlanIdOrderByDayCounterAsc(plan_id);
        if (listDay != null){
            return listDay.stream().map(DayMapper::toDayDTO).toList();
        }else{
            return null;
        }

    }

    @Transactional
    @Override
    public void upDay(Long dayId) {
        Day day = dayRepository.findById(dayId)
                .orElseThrow(() -> new EntityNotFoundException("Day id " + dayId + " not found"));

        if (day.getDayCounter() == 1) {
            throw new IllegalStateException("Cannot move up further");
        }

        Long planId = day.getPlan().getId();
        Day topDay = dayRepository
                .findByDayCounterAndPlanId(day.getDayCounter() - 1, planId);

        if (topDay == null) {
            throw new IllegalStateException("Top day not found for plan " + planId);
        }

        topDay.setDayCounter(0);
        dayRepository.save(topDay);

        day.setDayCounter(day.getDayCounter() - 1);
        dayRepository.save(day);

        topDay.setDayCounter(day.getDayCounter() + 1);
        dayRepository.save(topDay);
    }


    @Override
    public void downDay(Long day_id) {
        Day day = dayRepository.findById(day_id).orElseThrow(()->new EntityNotFoundException("Day with day_id: "+day_id+" is not found"));
        if (day.getDayCounter() == day.getPlan().getAvailableCounter()-1){
            throw new IllegalStateException("Cannot move further down");
        }
        Long plan_id = day.getPlan().getId();
        Day downDay = dayRepository.findByDayCounterAndPlanId(day.getDayCounter()+1,plan_id);

        if (downDay == null){
            throw new IllegalStateException("Down day not found for plan " + plan_id);
        }

        downDay.setDayCounter(0);
        dayRepository.save(downDay);

        day.setDayCounter(day.getDayCounter()+1);
        dayRepository.save(day);

        downDay.setDayCounter(day.getDayCounter()-1);
        dayRepository.save(downDay);
    }

    @Override
    public void deleteDay(Long day_id) {
        Day day = dayRepository.findById(day_id)
                .orElseThrow(() -> new EntityNotFoundException("Day with day_id: " + day_id + " is not found"));

        Plan plan = day.getPlan();


        boolean isLast = (day.getDayCounter() == plan.getAvailableCounter()-1);

        if (isLast) {
            plan.setAvailableCounter(plan.getAvailableCounter() - 1);
            planRepository.save(plan);
            dayRepository.deleteById(day_id);
            return;
        }

        List<Day> listDay =
                dayRepository.findAllByPlanIdAndDayCounterGreaterThanOrderByDayCounterAsc(
                        plan.getId(), day.getDayCounter());


        day.setDayCounter(0);
        dayRepository.save(day);

        for (Day dayup : listDay) {
            dayup.setDayCounter(dayup.getDayCounter() - 1);
            dayRepository.save(dayup);
        }

        plan.setAvailableCounter(plan.getAvailableCounter() - 1);
        planRepository.save(plan);

        dayRepository.deleteById(day_id);
    }
}
