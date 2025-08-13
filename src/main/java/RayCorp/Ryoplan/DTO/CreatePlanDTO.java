package RayCorp.Ryoplan.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreatePlanDTO {
    private String plan_name;
    private String description;
    private String location;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggal_mulai;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggal_selesai;
    private List<PlanUserDTO> userList;

    public CreatePlanDTO(){};
    public CreatePlanDTO(String plan_name,String description,String location,LocalDate tanggal_mulai, LocalDate tanggal_selesai,List<PlanUserDTO> userList){
        this.plan_name = plan_name;
        this.description = description;
        this.location = location;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_selesai = tanggal_selesai;
        this.userList = userList;
    }

    public List<PlanUserDTO> getUserList() {return userList;}
    public LocalDate getTanggal_mulai() {return tanggal_mulai;}
    public LocalDate getTanggal_selesai() {return tanggal_selesai;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public String getPlan_name() {return plan_name;}

    public void setPlan_name(String plan_name) {this.plan_name = plan_name;}
    public void setDescription(String description) {this.description = description;}
    public void setLocation(String location) {this.location = location;}
    public void setTanggal_mulai(LocalDate tanggal_mulai) {this.tanggal_mulai = tanggal_mulai;}
    public void setTanggal_selesai(LocalDate tanggal_selesai) {this.tanggal_selesai = tanggal_selesai;}
    public void setUserList(List<PlanUserDTO> userList) {this.userList = userList;}

}
