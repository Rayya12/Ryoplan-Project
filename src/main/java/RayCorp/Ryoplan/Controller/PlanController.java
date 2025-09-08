package RayCorp.Ryoplan.Controller;

import org.apache.catalina.connector.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import RayCorp.Ryoplan.DTO.CreatePlanDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Service.PlanService;
import RayCorp.Ryoplan.DTO.PlanListDTO;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;



    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/create")
    public ResponseEntity<PlanShowDTO> createPlan(@RequestBody CreatePlanDTO cDto){
        PlanShowDTO createdPlan = planService.createPlan(cDto);
        return ResponseEntity.ok(createdPlan);
    }

    @PostMapping("/delete/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Long planId){

        Boolean isDeleted = planService.deletePlan(planId);
        if (isDeleted){
            return ResponseEntity.ok("Plan has been deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot found the selected plan");
        }
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanShowDTO> getPlanFromId(@PathVariable Long plan){
        PlanShowDTO planShowDTO = planService.getPlanFromId(plan);
        if (planShowDTO != null){
            return ResponseEntity.ok(planShowDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/update/{planId}")
    public ResponseEntity<PlanShowDTO> updatePlan(@PathVariable Long planId,@RequestBody CreatePlanDTO createPlanDTO){
        PlanShowDTO planShowDTO = planService.updatePlan(createPlanDTO, planId);
        if (planShowDTO != null){
            return ResponseEntity.ok(planShowDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }





    



}
