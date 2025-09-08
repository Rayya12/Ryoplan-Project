package RayCorp.Ryoplan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import RayCorp.Ryoplan.DTO.PlanListDTO;
import RayCorp.Ryoplan.DTO.UserLoginDTO;
import RayCorp.Ryoplan.DTO.UserRegisterDTO;
import RayCorp.Ryoplan.Service.AuthService;
import RayCorp.Ryoplan.Service.PlanService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PlanService planService;


    @GetMapping("/{userId}/plans")
    public ResponseEntity<List<PlanListDTO>> getPlanList(@PathVariable Long userId, @RequestParam(required = false) String name){
        List<PlanListDTO> planListForUser = planService.getPlanListForUserWithName(name,userId);
        return ResponseEntity.ok(planListForUser);
    }

}
