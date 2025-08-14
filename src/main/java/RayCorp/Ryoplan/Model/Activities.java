package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="activities")
public class Activities {

    public Activities(){};
    public Activities(Day day, String activityName, BigDecimal budget, LocalTime jamMulai, LocalTime jamSelesai){
        this.day = day;
        this.activityName = activityName;
        this.budget = budget;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(45)",name = "activity_name")
    private String activityName;

    @Column(columnDefinition = "DECIMAL(12,2) DEFAULT 0",name="budget")
    private BigDecimal budget;

    @Column(name = "jam_mulai")
    private LocalTime jamMulai;

    @Column(name= "jam_selesai")
    private LocalTime jamSelesai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id",nullable = false)
    private Day day;

    public Long getId() {return id;}
    public BigDecimal getBudget() {return budget;}
    public LocalTime getJamMulai() {return jamMulai;}
    public LocalTime getJamSelesai() {return jamSelesai;}
    public String getActivityName() {return activityName;}
    public Day getDay() {return day;}

    public void setId(Long id) {this.id = id;}
    public void setActivityName(String activityName) {this.activityName = activityName;}
    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setJamMulai(LocalTime jamMulai) {this.jamMulai = jamMulai;}
    public void setJamSelesai(LocalTime jamSelesai) {this.jamSelesai = jamSelesai;}
    public void setDay(Day day) {this.day = day;}
}
