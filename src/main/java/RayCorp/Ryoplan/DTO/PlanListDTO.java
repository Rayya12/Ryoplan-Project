package RayCorp.Ryoplan.DTO;

import RayCorp.Ryoplan.Model.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlanListDTO {
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

}
