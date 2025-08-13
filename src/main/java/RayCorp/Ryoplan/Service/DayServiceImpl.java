package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.*;
import RayCorp.Ryoplan.Mapper.DayMapper;
import RayCorp.Ryoplan.Mapper.PlanMapper;
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
        new_day.setDay_title(dto.getDay_title());
        new_day.setDescription(dto.getDescription());
        new_day.setBiaya_hotel(dto.getBiaya_hotel());

        // untuk counter hari + 1
        new_day.setDay_counter(plan.getAvailable_counter());
        plan.setAvailable_counter(plan.getAvailable_counter()+1);

        if (dto.getList_aktivitas() != null) {
            dto.getList_aktivitas().forEach(aktivitasDto -> {
                Activities a = new Activities();
                a.setActivity_name(aktivitasDto.getActivity_name());
                a.setBudget(aktivitasDto.getBudget());
                a.setJam_mulai(aktivitasDto.getJam_mulai());
                a.setJam_selesai(aktivitasDto.getJam_selesai());
                new_day.addAktivitas(a); // ini set a.setDay(this) + add ke list
            });
        }

        Day savedDay = dayRepository.save(new_day);
        return DayMapper.toDayDTO(savedDay);

    }

    @Override
    public DayDTO updateDay(Long day_id,DayDTO dto) {
        Day day = dayRepository.findById(day_id).orElseThrow(()->new EntityNotFoundException("Day with id: "+day_id+" not found"));
        Plan plan = planRepository.findById(dto.getPlan().getPlan_id()).orElseThrow(()->new EntityNotFoundException("Plan with id: "+dto.getPlan().getPlan_id()+" not found" ));
        day.setDay_title(dto.getDay_title());
        day.setDescription(dto.getDescription());
        day.setPlan(plan);
        day.setBiaya_hotel(dto.getBiaya_hotel());

        if (dto.getList_aktivitas() != null) {
            dto.getList_aktivitas().forEach(aktivitasDto -> {
                Activities a = new Activities();
                a.setActivity_name(aktivitasDto.getActivity_name());
                a.setBudget(aktivitasDto.getBudget());
                a.setJam_mulai(aktivitasDto.getJam_mulai());
                a.setJam_selesai(aktivitasDto.getJam_selesai());
                day.addAktivitas(a); // ini set a.setDay(this) + add ke list
            });
        }

        Day saved_day = dayRepository.save(day);
        return DayMapper.toDayDTO(saved_day);
    }

    @Override
    public List<DayDTO> listDayByPlan(Long plan_id) {
        List<Day> listDay = dayRepository.findAllByPlan_idOrderByDay_CounterAsc(plan_id);
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

        if (day.getDay_counter() == 1) {
            throw new IllegalStateException("Cannot move up further");
        }

        Long planId = day.getPlan().getPlan_id();
        Day topDay = dayRepository
                .findByDay_counterAndPlan_id(day.getDay_counter() - 1, planId);

        if (topDay == null) {
            throw new IllegalStateException("Top day not found for plan " + planId);
        }

        topDay.setDay_counter(0);
        dayRepository.save(topDay);

        day.setDay_counter(day.getDay_counter() - 1);
        dayRepository.save(day);

        topDay.setDay_counter(day.getDay_counter() + 1);
        dayRepository.save(topDay);
    }

    @Override
    public void downDay(Long day_id) {

    }

    @Override
    public void deleteDay(Long day_id) {

    }
}
