package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.CreatePlanDTO;
import RayCorp.Ryoplan.DTO.PlanListDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Model.Plan;
import io.micrometer.common.lang.Nullable;

import java.util.List;

public interface PlanService {
    public PlanShowDTO createPlan(CreatePlanDTO cdto);
    public PlanShowDTO updatePlan(CreatePlanDTO cdto,Long plan_id);
    public Boolean deletePlan(Long plan_id);
    public PlanShowDTO getPlanFromId(Long plan_id);
    public List<PlanListDTO> getPlanListForUserWithName(@Nullable String name,Long userId);


}
