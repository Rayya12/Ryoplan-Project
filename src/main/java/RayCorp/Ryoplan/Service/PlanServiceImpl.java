package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.CreatePlanDTO;
import RayCorp.Ryoplan.DTO.PlanListDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Model.Plan;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Override
    public PlanShowDTO createPlan(CreatePlanDTO cdto) {
        Plan plan = new Plan();
        plan.setPlanName(cdto.getPlan_name());
        return null;
    }

    @Override
    public PlanShowDTO updatePlan(CreatePlanDTO cdto) {
        return null;
    }

    @Override
    public void deletePlan(Long plan_id) {

    }

    @Override
    public PlanShowDTO getPlanFromId(Long plan_id) {
        return null;
    }

    @Override
    public List<PlanListDTO> getPlanListForUser(Long plan_id, Long user_id) {
        return List.of();
    }
}
