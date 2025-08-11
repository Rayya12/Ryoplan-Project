package RayCorp.Ryoplan.DTO;

public class PlanUserDTO {
    private String user_name;
    private Long id;

    public PlanUserDTO(){}
    public PlanUserDTO(String user_name,Long id){
        this.user_name = user_name;
        this.id = id;
    }

    public Long getId() {return id;}
    public String getUser_name() {return user_name;}

    public void setId(Long id) {this.id = id;}
    public void setUser_name(String user_name) {this.user_name = user_name;}
}
