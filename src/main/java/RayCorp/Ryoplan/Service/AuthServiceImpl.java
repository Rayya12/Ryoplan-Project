package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.UserLoginDTO;
import RayCorp.Ryoplan.DTO.UserRegisterDTO;
import RayCorp.Ryoplan.Model.User;
import RayCorp.Ryoplan.Repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

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
    public String login(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findByEmail(userLoginDTO.getEmail());

        if (user.isPresent()) {
            User u = user.get(); // ambil data dari Optional
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(),u.getPassword()));
            if (authentication.isAuthenticated()){
                return jwtService.generateToken(u.getEmail());
            }else{
                throw new RuntimeException("Invalid email or password");
            }
            

        } else {
            throw new RuntimeException("Invalid email or password");
        }
}



}
