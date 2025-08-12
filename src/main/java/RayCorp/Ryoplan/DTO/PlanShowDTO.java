package RayCorp.Ryoplan.DTO;

import RayCorp.Ryoplan.Model.Day;
import RayCorp.Ryoplan.Model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PlanShowDTO {
    private Long plan_id;
    private String plan_name;
    private String description;
    private String location;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggal_mulai;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggal_selesai;

    private BigDecimal budget;

    private Float progress;

    private List<PlanUserDTO> userList;

    private List<PlanDayDTO> list_day;

    private Integer available_counter;

    public PlanShowDTO(){};
    public PlanShowDTO(Long plan_id,String plan_name,String description,String location,LocalDate tanggal_mulai,LocalDate tanggal_selesai,BigDecimal budget,Float progress,List<PlanUserDTO> userList,List<PlanDayDTO> list_day,Integer available_counter){
        this.plan_id = plan_id;
        this.plan_name = plan_name;
        this.description = description;
        this.location = location;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_selesai = tanggal_selesai;
        this.budget = budget;
        this.progress = progress;
        this.userList = userList;
        this.list_day = list_day;
        this.available_counter = available_counter;
    }

    public BigDecimal getBudget() {return budget;}
    public Float getProgress() {return progress;}
    public List<PlanDayDTO> getList_day() {return list_day;}
    public List<PlanUserDTO> getUserList() {return userList;}
    public LocalDate getTanggal_mulai() {return tanggal_mulai;}
    public LocalDate getTanggal_selesai() {return tanggal_selesai;}
    public Long getPlan_id() {return plan_id;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public String getPlan_name() {return plan_name;}
    public Integer getAvailable_counter() {return available_counter;}

    public void setDescription(String description) {this.description = description;}
    public void setPlan_name(String plan_name) {this.plan_name = plan_name;}
    public void setBudget(BigDecimal budget) {this.budget = budget;}
    public void setList_day(List<PlanDayDTO> list_day) {this.list_day = list_day;}
    public void setLocation(String location) {this.location = location;}
    public void setPlan_id(Long plan_id) {this.plan_id = plan_id;}
    public void setProgress(Float progress) {this.progress = progress;}
    public void setTanggal_mulai(LocalDate tanggal_mulai) {this.tanggal_mulai = tanggal_mulai;}
    public void setTanggal_selesai(LocalDate tanggal_selesai) {this.tanggal_selesai = tanggal_selesai;}
    public void setUserList(List<PlanUserDTO> userList) {this.userList = userList;}
    public void setAvailable_counter(Integer available_counter) {this.available_counter = available_counter;}
}
