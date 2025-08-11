package RayCorp.Ryoplan.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Email
    @NotBlank
    public String email;

    @NotBlank
    @Size(min = 8)
    public String password;

    public UserLoginDTO(){};
    public UserLoginDTO(String email,String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {return email;}
    public String getPassword() {return password;}

    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
}
