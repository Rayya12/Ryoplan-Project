package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.CreatePlanDTO;
import RayCorp.Ryoplan.DTO.PlanListDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Mapper.PlanMapper;
import RayCorp.Ryoplan.Model.Plan;
import RayCorp.Ryoplan.Model.User;
import RayCorp.Ryoplan.Repositories.PlanRepository;
import RayCorp.Ryoplan.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public PlanShowDTO createPlan(CreatePlanDTO cdto) {
        Plan plan = new Plan();
        plan.setPlanName(cdto.getPlan_name());
        plan.setDescription(cdto.getDescription());
        plan.setLocation(cdto.getLocation());
        plan.setTanggalMulai(cdto.getTanggal_mulai());
        plan.setTanggalSelesai(cdto.getTanggal_selesai());
        cdto.getUserList().forEach(userDTO -> {
            User user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            plan.addUser(user);
        });

        Plan savedPlan = planRepository.save(plan);

        return PlanMapper.toPlanShowDTO(savedPlan);
    }

    @Override
    public PlanShowDTO updatePlan(CreatePlanDTO cdto,Long plan_id) {
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
