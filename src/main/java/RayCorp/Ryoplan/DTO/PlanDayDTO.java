package RayCorp.Ryoplan.DTO;

import java.math.BigDecimal;

public class PlanDayDTO {
    private Long day_id;
    private Integer day_counter;
    private String day_title;
    private BigDecimal budget;

    public PlanDayDTO(){}
    public PlanDayDTO(Long day_id,Integer day_counter,String day_title,BigDecimal budget){
        this.day_id = day_id;
        this.day_counter = day_counter;
        this.day_title = day_title;
        this.budget = budget;
    }

    public BigDecimal getBudget() {return budget;}
    public Integer getDay_counter() {return day_counter;}
    public Long getDay_id() {return day_id;}
    public String getDay_title() {return day_title;}

    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setDay_counter(Integer day_counter) {this.day_counter = day_counter;}
    public void setDay_id(Long day_id) {this.day_id = day_id;}
    public void setDay_title(String day_title) {this.day_title = day_title;}
}
