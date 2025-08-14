package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    public User(){}
    public User(String email, String password, String username, LocalDate dob, Gender gender, String phoneNumber, String address, String country){
        this.email = email;
        this.password = password;
        this.username = username;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    @Email
    private String email;

    @Column(name = "password",columnDefinition = "VARCHAR(255)",nullable = false)
    private String password;

    @Column(name="username",columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(name="dob")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phoneNumber",columnDefinition = "VARCHAR(20)")
    private String phoneNumber;

    @Column(name = "address",columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(name = "country",columnDefinition = "VARCHAR(255)")
    private String country;

    @ManyToMany
    @JoinTable(
            name = "user_plan",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private List<Plan> planList = new ArrayList<Plan>();

    public Long getId() {return id;}
    public Gender getGender() {return gender;}
    public LocalDate getDob() {return dob;}
    public String getAddress() {return address;}
    public String getCountry() {return country;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getUsername() {return username;}
    public List<Plan> getPlanList() {return planList;}

    public void setAddress(String address) {this.address = address;}
    public void setCountry(String country) {this.country = country;}
    public void setDob(LocalDate dob) {this.dob = dob;}
    public void setEmail(String email) {this.email = email;}
    public void setGender(Gender gender) {this.gender = gender;}
    public void setPassword(String password) {this.password = password;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setId(Long id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPlanList(List<Plan> planList) {this.planList = planList;}

    public void addPlan(Plan p) {
        this.planList.add(p);
        p.getUserList().add(this);
    }

    public void removePlan(Plan p) {
        this.planList.remove(p);
        p.getUserList().remove(this);
    }
}
