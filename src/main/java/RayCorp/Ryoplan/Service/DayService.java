package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.DayDTO;
import java.util.List;

public interface DayService {
    public DayDTO createDay(DayDTO dto);
    public DayDTO updateDay(DayDTO dto);
    public List<DayDTO> listDayByPlan(Long plan_id);
    public void upDay(Long day_id);
    public void downDay(Long day_id);
    public void deleteDay(Long day_id);
}
