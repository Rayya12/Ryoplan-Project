package RayCorp.Ryoplan.DTO;

import RayCorp.Ryoplan.Model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserRegisterDTO {

    @NotBlank(message = "Username tidak boleh kosong")
    private String username;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password minimal 8 karakter")
    private String password;

    @Past(message = "Tanggal lahir harus di masa lalu")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    // Jika wajib, tambahkan @NotNull
    private Gender gender;

    private String phoneNumber;

    @Size(max = 200, message = "Alamat maksimal 200 karakter")
    private String address;

    @Size(max = 60, message = "Negara maksimal 60 karakter")
    private String country;

    public UserRegisterDTO() {}

    public UserRegisterDTO(String username, String email, String password, LocalDate dob,
                           Gender gender, String phoneNumber, String address, String country) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
    }

    // getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public LocalDate getDob() { return dob; }
    public Gender getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getCountry() { return country; }

    // setters
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setCountry(String country) { this.country = country; }
}