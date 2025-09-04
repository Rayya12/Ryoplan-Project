package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.UserLoginDTO;
import RayCorp.Ryoplan.DTO.UserRegisterDTO;

public interface AuthService {
    public void register(UserRegisterDTO userRegisterDTO);
    public String login(UserLoginDTO userLoginDTO);

}
