package RayCorp.Ryoplan.DTO;

import RayCorp.Ryoplan.Model.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlanListDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long plan_id;
    public String plan_name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate tanggal_mulai;
    public BigDecimal budget;
    public Float progress;

    public PlanListDTO(){}
    public PlanListDTO(Long plan_id,String plan_name,LocalDate tanggal_mulai,BigDecimal budget,Float progress){
        this.plan_id = plan_id;
        this.plan_name = plan_name;
        this.tanggal_mulai = tanggal_mulai;
        this.budget = budget;
        this.progress = progress;
    }

    public Long getPlan_id() {return plan_id;}
    public String getPlan_name() {return plan_name;}
    public BigDecimal getBudget() {return budget;}
    public Float getProgress() {return progress;}
    public LocalDate getTanggal_mulai() {return tanggal_mulai;}

    public void setPlan_name(String plan_name) {this.plan_name = plan_name;}
    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setPlan_id(Long plan_id) {this.plan_id = plan_id;}
    public void setProgress(Float progress) {this.progress = progress;}
    public void setTanggal_mulai(LocalDate tanggal_mulai) {this.tanggal_mulai = tanggal_mulai;}
}
