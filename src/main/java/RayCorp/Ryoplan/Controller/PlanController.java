package RayCorp.Ryoplan.Controller;

import org.apache.catalina.connector.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RayCorp.Ryoplan.DTO.CreatePlanDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Service.PlanService;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @RequestMapping("/create")
    public ResponseEntity<PlanShowDTO> createPlan(@RequestBody CreatePlanDTO cDto){
        System.out.println("DTO MASUK:"+cDto.getPlan_name());
        PlanShowDTO createdPlan = planService.createPlan(cDto);
        return ResponseEntity.ok(createdPlan);
    }

}
