package RayCorp.Ryoplan.DTO;

import java.math.BigDecimal;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ActivitiesDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long activity_id;
    // ini menyebabkan dia akan muncul saat response tapi tidak muncul saat request

    private String activity_name;
    private BigDecimal budget;
    private LocalTime jam_mulai;
    private LocalTime jam_selesai;

    public ActivitiesDTO(){}
    public ActivitiesDTO(Long activity_id, String activity_name, BigDecimal budget, LocalTime jam_mulai, LocalTime jam_selesai){
        this.activity_id = activity_id;
        this.activity_name = activity_name;
        this.budget = budget;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
    };

    public String getActivity_name() {return activity_name;}
    public LocalTime getJam_selesai() {return jam_selesai;}
    public LocalTime getJam_mulai() {return jam_mulai;}
    public BigDecimal getBudget() {return budget;}
    public Long getActivity_id() {return activity_id;}

    public void setJam_selesai(LocalTime jam_selesai) {this.jam_selesai = jam_selesai;}
    public void setJam_mulai(LocalTime jam_mulai) {this.jam_mulai = jam_mulai;}
    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setActivity_name(String activity_name) {this.activity_name = activity_name;}
    public void setActivity_id(Long activity_id) {this.activity_id = activity_id;}

}
