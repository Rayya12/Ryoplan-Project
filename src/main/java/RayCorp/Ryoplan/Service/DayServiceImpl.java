package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.DayDTO;
import RayCorp.Ryoplan.Model.Day;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DayServiceImpl implements DayService{
    @Override
    public DayDTO createDay(DayDTO dto) {
        Day new_day = new Day();
        new_day.setDay_title(dto.getDay_title());
        new_day.setDescription(dto.getDescription());
        new_day.setBiaya_hotel(dto.getBiaya_hotel());
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
