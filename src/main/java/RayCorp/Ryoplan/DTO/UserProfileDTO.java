package RayCorp.Ryoplan.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserProfileDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;
    private String username;

    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String gender;
    private String phoneNumber;
    @Size(max = 200, message = "Adress maximum length is 200")
    private String address;
    @Size(max = 60, message = "Country maximum length is 60")
    private String country;

    private String photoUrl;
    private String photoContentType;
    private Long photoSizeBytes;
    private String photoEtag;

    public UserProfileDTO(){};
    public UserProfileDTO(Long id, String email, String username, LocalDate dob, String gender, String phoneNumber, String address, String country,
                          String photoUrl, String photoContentType,
                          Long photoSizeBytes, String photoEtag) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.photoUrl = photoUrl;
        this.photoContentType = photoContentType;
        this.photoSizeBytes = photoSizeBytes;
        this.photoEtag = photoEtag;
    }

    public LocalDate getDob() {return dob;}
    public Long getId() {return id;}
    public Long getPhotoSizeBytes() {return photoSizeBytes;}
    public String getAddress() {return address;}
    public String getCountry() {return country;}
    public String getEmail() {return email;}
    public String getGender() {return gender;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getPhotoContentType() {return photoContentType;}
    public String getPhotoEtag() {return photoEtag;}
    public String getPhotoUrl() {return photoUrl;}
    public String getUsername() {return username;}

    public void setAddress(String address) {this.address = address;}
    public void setCountry(String country) {this.country = country;}
    public void setDob(LocalDate dob) {this.dob = dob;}
    public void setEmail(String email) {this.email = email;}
    public void setGender(String gender) {this.gender = gender;}
    public void setId(Long id) {this.id = id;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setPhotoContentType(String photoContentType) {this.photoContentType = photoContentType;}
    public void setPhotoEtag(String photoEtag) {this.photoEtag = photoEtag;}
    public void setPhotoSizeBytes(Long photoSizeBytes) {this.photoSizeBytes = photoSizeBytes;}
    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl;}
    public void setUsername(String username) {this.username = username;}
}
