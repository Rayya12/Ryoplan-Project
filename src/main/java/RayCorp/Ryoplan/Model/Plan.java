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
    public Plan(String plan_name,String description){
        this.plan_name = plan_name;
        this.description = description;
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plan_id;

    @Column(name = "plan_name",columnDefinition = "VARCHAR(40)",nullable = false)
    private String plan_name;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "location",columnDefinition = "VARCHAR(30)")
    private String location;

    @Column(name = "tanggal_mulai")
    private LocalDate tanggal_mulai;

    @Column(name="tanggal_selesai")
    private LocalDate tanggal_selesai;

    @Column(name="budget_rn",columnDefinition = "DECIMAL(12,2)")
    private BigDecimal budget_rn;

    @OneToMany(mappedBy = "plan",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Day> list_day = new ArrayList<Day>();

    public void setDescription(String description) {this.description = description;}
    public void setPlan_id(Long plan_id) {this.plan_id = plan_id;}
    public void setPlan_name(String plan_name) {this.plan_name = plan_name;}
    public void setList_day(List<Day> list_day) {this.list_day = list_day;}
    public void setLocation(String location) {this.location = location;}
    public void setBudget_rn(BigDecimal budget_rn) {this.budget_rn = budget_rn;}
    public void setTanggal_mulai(LocalDate tanggal_mulai) {this.tanggal_mulai = tanggal_mulai;}
    public void setTanggal_selesai(LocalDate tanggal_selesai) {this.tanggal_selesai = tanggal_selesai;}

    public Long getPlan_id() {return plan_id;}
    public List<Day> getList_day() {return list_day;}
    public String getDescription() {return description;}
    public String getPlan_name() {return plan_name;}
    public String getLocation() {return location;}
    public BigDecimal getBudget_rn() {return budget_rn;}
    public LocalDate getTanggal_mulai() {return tanggal_mulai;}
    public LocalDate getTanggal_selesai() {return tanggal_selesai;}

    public BigDecimal getBudget_plan(){
        if (list_day == null || list_day.isEmpty()){
            return BigDecimal.ZERO;
        }else{
            BigDecimal total = BigDecimal.ZERO;
            for (Day hari:list_day){
                total.add(hari.getBudgetForDay());
            }
            return total;
        }
    }





}
