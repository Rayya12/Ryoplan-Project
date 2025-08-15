package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.UserLoginDTO;
import RayCorp.Ryoplan.DTO.UserRegisterDTO;
import RayCorp.Ryoplan.Model.User;
import RayCorp.Ryoplan.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())){
            throw new RuntimeException("Email is already used");
        }

        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        user.setAddress(userRegisterDTO.getAddress());
        user.setCountry(userRegisterDTO.getCountry());
        user.setDob(userRegisterDTO.getDob());
        user.setGender(userRegisterDTO.getGender());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setUsername(userRegisterDTO.getUsername());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());

        userRepository.save(user);
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.email);
        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
    }



}
