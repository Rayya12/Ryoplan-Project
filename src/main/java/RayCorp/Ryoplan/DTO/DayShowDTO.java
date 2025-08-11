package RayCorp.Ryoplan.DTO;

import RayCorp.Ryoplan.Model.Plan;

import java.math.BigDecimal;

public class DayShowDTO {
    private Long day_id;
    private String day_title;
    private String description;
    private Integer day_counter;
    private BigDecimal biaya_hotel;
    private PlanShowDTO plan;


    public DayShowDTO(){}
    public DayShowDTO(Long day_id,String day_title,String description,Integer day_counter,BigDecimal biaya_hotel,PlanShowDTO plan){
        this.day_id = day_id;
        this.day_title = day_title;
        this.description = description;
        this.day_counter = day_counter;
        this.biaya_hotel = biaya_hotel;
        this.plan = plan;
    }

    public BigDecimal getBiaya_hotel() {return biaya_hotel;}
    public Integer getDay_counter() {return day_counter;}
    public PlanShowDTO getPlan() {return plan;}
    public Long getDay_id() {return day_id;}
    public String getDay_title() {return day_title;}
    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
    public void setBiaya_hotel(BigDecimal biaya_hotel) {this.biaya_hotel = biaya_hotel;}
    public void setDay_counter(Integer day_counter) {this.day_counter = day_counter;}
    public void setDay_id(Long day_id) {this.day_id = day_id;}
    public void setDay_title(String day_title) {this.day_title = day_title;}
    public void setPlan(PlanShowDTO plan) {this.plan = plan;}
}

