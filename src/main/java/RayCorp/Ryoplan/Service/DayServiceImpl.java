package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.DayDTO;
import RayCorp.Ryoplan.DTO.PlanDayDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.DTO.PlanUserDTO;
import RayCorp.Ryoplan.Mapper.PlanMapper;
import RayCorp.Ryoplan.Model.Activities;
import RayCorp.Ryoplan.Model.Day;
import RayCorp.Ryoplan.Model.Plan;
import RayCorp.Ryoplan.Repositories.DayRepository;
import RayCorp.Ryoplan.Repositories.PlanRepository;
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

        DayDTO response = new DayDTO();
        response.setDay_id(savedDay.getDay_id());
        response.setDay_counter(savedDay.getDay_counter());
        response.setDay_title(savedDay.getDay_title());
        PlanShowDTO planShowDTO = PlanMapper.toPlanShowDTO(savedDay.getPlan());


    }

    @Override
    public DayDTO updateDay(DayDTO dto) {
        return null;
    }

    @Override
    public List<DayDTO> listDayByPlan(Long plan_id) {
        return List.of();
    }

    @Override
    public void upDay(Long day_id) {

    }

    @Override
    public void downDay(Long day_id) {

    }

    @Override
    public void deleteDay(Long day_id) {

    }
}
