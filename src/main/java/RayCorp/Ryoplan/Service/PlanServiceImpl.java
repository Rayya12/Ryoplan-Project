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

import java.util.ArrayList;
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
        System.out.println(cdto.getPlan_name());
        plan.setPlanName(cdto.getPlan_name());
        plan.setDescription(cdto.getDescription());
        plan.setLocation(cdto.getLocation());
        plan.setTanggalMulai(cdto.getTanggal_mulai());
        plan.setTanggalSelesai(cdto.getTanggal_selesai());
        cdto.getUserList().forEach(userDTO -> {
            User user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id"+userDTO.getId()+"not found"));
            plan.addUser(user);
        });

        Plan savedPlan = planRepository.save(plan);

        return PlanMapper.toPlanShowDTO(savedPlan);
    }

    @Override
    public PlanShowDTO updatePlan(CreatePlanDTO cdto,Long plan_id) {
        Plan plan = planRepository.findById(plan_id).orElseThrow(()->new EntityNotFoundException("Plan with id "+plan_id+" not found"));
        plan.setPlanName(cdto.getPlan_name());
        plan.setDescription(cdto.getDescription());
        plan.setLocation(cdto.getLocation());
        plan.setTanggalMulai(cdto.getTanggal_mulai());
        plan.setTanggalSelesai(cdto.getTanggal_selesai());
        List<User> listUser = new ArrayList<User>();
        List<User> users = cdto.getUserList().stream()
                .map(dto -> userRepository.findById(dto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found")))
                .toList();
        plan.replaceUsers(users);

        Plan saved_plan = planRepository.save(plan);
        return PlanMapper.toPlanShowDTO(saved_plan);
    }

    @Override
    public void deletePlan(Long plan_id) {
        planRepository.deleteById(plan_id);
    }

    @Override
    public PlanShowDTO getPlanFromId(Long plan_id) {
        return PlanMapper.toPlanShowDTO(planRepository.findById(plan_id).orElseThrow(()->new EntityNotFoundException("Plan with id: "+plan_id+" is not found")));
    }

    @Override
    public List<PlanListDTO> getPlanListForUser(Long user_id) {
        List<Plan> listPlanById = userRepository.findById(user_id).orElseThrow(()->new EntityNotFoundException("Cannot found user with id: "+user_id)).getPlanList();
        List<PlanListDTO> dtoPlanList = new ArrayList<>();
        if (listPlanById != null && !listPlanById.isEmpty()){
            dtoPlanList = listPlanById.stream().map(planku -> {
                PlanListDTO dto = new PlanListDTO();
                dto.setPlan_name(planku.getPlanName());
                dto.setBudget(planku.getBudgetPlan());
                dto.setTanggal_mulai(planku.getTanggalMulai());
                dto.setProgress(planku.getProgress());
                return dto;
            }).toList();
        }
        return dtoPlanList;
    }
}
