package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.PlanListDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Model.Plan;
import java.util.List;

public interface PlanService {
    public PlanShowDTO createPlan();
    public PlanShowDTO updatePlan();
    public void deletePlan(Long plan_id);
    public PlanShowDTO getPlanFromId(Long plan_id);
    public List<PlanListDTO> getPlanListForUser(Long plan_id, Long user_id);


}
