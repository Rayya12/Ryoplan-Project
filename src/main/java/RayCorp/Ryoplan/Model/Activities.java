package RayCorp.Ryoplan.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="activities")
public class Activities {

    public Activities(){};
    public Activities(Day day,String activity_name,BigDecimal budget,LocalTime jam_mulai,LocalTime jam_selesai){
        this.day = day;
        this.activity_name = activity_name;
        this.budget = budget;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activity_id;

    @Column(columnDefinition = "VARCHAR(45)",name = "activity_name")
    private String activity_name;

    @Column(columnDefinition = "DECIMAL(12,2) DEFAULT 0",name="budget")
    private BigDecimal budget;

    @Column(name = "jam_mulai")
    private LocalTime jam_mulai;

    @Column(name= "jam_selesai")
    private LocalTime jam_selesai;

    @ManyToOne
    @JoinColumn(name = "day_id",nullable = false)
    private Day day;

    public Long getActivity_id() {return activity_id;}
    public BigDecimal getBudget() {return budget;}
    public LocalTime getJam_mulai() {return jam_mulai;}
    public LocalTime getJam_selesai() {return jam_selesai;}
    public String getActivity_name() {return activity_name;}
    public Day getDay() {return day;}

    public void setActivity_id(Long activity_id) {this.activity_id = activity_id;}
    public void setActivity_name(String activity_name) {this.activity_name = activity_name;}
    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setJam_mulai(LocalTime jam_mulai) {this.jam_mulai = jam_mulai;}
    public void setJam_selesai(LocalTime jam_selesai) {this.jam_selesai = jam_selesai;}
    public void setDay(Day day) {this.day = day;}
}
