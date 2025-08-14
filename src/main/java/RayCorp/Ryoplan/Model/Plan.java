package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {

    public Plan(){};
    public Plan(String planName, String description,String location,LocalDate tanggalMulai,LocalDate tanggalSelesai){
        this.planName = planName;
        this.description = description;
        this.location = location;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.budgetRn = BigDecimal.ZERO;

    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planName",columnDefinition = "VARCHAR(40)",nullable = false)
    private String planName;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "location",columnDefinition = "VARCHAR(30)")
    private String location;

    @Column(name = "tanggalMulai")
    private LocalDate tanggalMulai;

    @Column(name="tanggalSelesai")
    private LocalDate tanggalSelesai;

    @Column(name="budgetRn",columnDefinition = "DECIMAL(12,2) DEFAULT 0")
    private BigDecimal budgetRn;

    @Column(name="availableCounter")
    private Integer availableCounter = 1;

    @OneToMany(mappedBy = "plan",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Day> listDay = new ArrayList<Day>();

    @ManyToMany(mappedBy = "planList")
    private List<User> userList = new ArrayList<User>();

    public void setDescription(String description) {this.description = description;}
    public void setId(Long id) {this.id = id;}
    public void setPlanName(String planName) {this.planName = planName;}
    public void setListDay(List<Day> listDay) {this.listDay = listDay;}
    public void setLocation(String location) {this.location = location;}
    public void setBudgetRn(BigDecimal budgetRn) {this.budgetRn = budgetRn;}
    public void setTanggalMulai(LocalDate tanggalMulai) {this.tanggalMulai = tanggalMulai;}
    public void setTanggalSelesai(LocalDate tanggalSelesai) {this.tanggalSelesai = tanggalSelesai;}
    public void setUserList(List<User> userList) {this.userList = userList;}
    public void setAvailableCounter(Integer availableCounter) {this.availableCounter = availableCounter;}


    public Long getId() {return id;}
    public List<Day> getListDay() {return listDay;}
    public String getDescription() {return description;}
    public String getPlanName() {return planName;}
    public String getLocation() {return location;}
    public BigDecimal getBudgetRn() {return budgetRn;}
    public LocalDate getTanggalMulai() {return tanggalMulai;}
    public LocalDate getTanggalSelesai() {return tanggalSelesai;}
    public List<User> getUserList() {return userList;}
    public Integer getAvailableCounter() {return availableCounter;}

    public BigDecimal getBudgetPlan(){
        if (listDay == null || listDay.isEmpty()){
            return BigDecimal.ZERO;
        }else{
            BigDecimal total = BigDecimal.ZERO;
            for (Day hari: listDay){
                total = total.add(hari.getBudgetForDay());
            }
            return total;
        }
    }

    public Float getProgress(){
        return (this.getBudgetPlan().subtract(this.getBudgetRn())).divide(this.getBudgetPlan()).multiply(BigDecimal.valueOf(100)).floatValue();
    }

    public void addUser(User user){
        if (userList.add(user)){
            user.addPlan(this);
        }
    }

    public void removeUser(User user) {
        if(userList.remove(user)){
            user.removePlan(this);
        }
    };

    public void addDay(Day day) {
        listDay.add(day);
        day.setPlan(this); // jaga sinkronisasi dua arah
    }

    public void removeDay(Day day) {
        listDay.remove(day);     // hapus dari koleksi Plan
        day.setPlan(null);       // putuskan hubungan di sisi Day
    }





}
