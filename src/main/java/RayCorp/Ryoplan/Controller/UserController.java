package RayCorp.Ryoplan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RayCorp.Ryoplan.DTO.UserLoginDTO;
import RayCorp.Ryoplan.DTO.UserRegisterDTO;
import RayCorp.Ryoplan.Service.AuthService;

@RestController
public class UserController {

    @Autowired
    private AuthService AuthService;

    
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO){
        return AuthService.login(userLoginDTO);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDTO userRegisterDTO){
        return AuthService.register(userRegisterDTO);
    }



}
