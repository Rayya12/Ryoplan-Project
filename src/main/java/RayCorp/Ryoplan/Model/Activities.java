package RayCorp.Ryoplan.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="aktivitas")
public class Activities {

    public Activities(){};
    public Activities(String activity_id,String activity_name,BigDecimal budget,LocalTime jam_mulai,LocalTime jam_selesai){
        this.activity_id = activity_id;
        this.activity_name = activity_name;
        this.budget = budget;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
    };

    @Id
    @Column(length=12,columnDefinition="CHAR(12)",name = "activity_id")
    private String activity_id;

    @Column(columnDefinition = "VARCHAR(45)",name = "activity_name")
    private String activity_name;

    @Column(columnDefinition = "DECIMAL(12,2) DEFAULT 0",name="budget")
    private BigDecimal budget;

    @Column(name = "jam_mulai")
    private LocalTime jam_mulai;

    @Column(name= "jam_selesai")
    private LocalTime jam_selesai;

    public String getActivity_id() {return activity_id;}
    public BigDecimal getBudget() {return budget;}
    public LocalTime getJam_mulai() {return jam_mulai;}
    public LocalTime getJam_selesai() {return jam_selesai;}
    public String getActivity_name() {return activity_name;}

    public void setActivity_id(String activity_id) {this.activity_id = activity_id;}
    public void setActivity_name(String activity_name) {this.activity_name = activity_name;}
    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setJam_mulai(LocalTime jam_mulai) {this.jam_mulai = jam_mulai;}
    public void setJam_selesai(LocalTime jam_selesai) {this.jam_selesai = jam_selesai;}
}
