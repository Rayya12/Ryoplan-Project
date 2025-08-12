package RayCorp.Ryoplan.Mapper;

import RayCorp.Ryoplan.DTO.PlanDayDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.DTO.PlanUserDTO;
import RayCorp.Ryoplan.Model.Plan;

public class PlanMapper {
    public static PlanShowDTO toPlanShowDTO(Plan plan){
        PlanShowDTO planShowDTO = new PlanShowDTO();
        planShowDTO.setPlan_id(plan.getPlan_id());
        planShowDTO.setPlan_name(plan.getPlan_name());
        planShowDTO.setDescription(plan.getDescription());
        planShowDTO.setBudget(plan.getBudget_plan());
        planShowDTO.setAvailable_counter(plan.getAvailable_counter());
        planShowDTO.setTanggal_mulai(plan.getTanggal_mulai());
        planShowDTO.setTanggal_selesai(plan.getTanggal_selesai());
        planShowDTO.setLocation(plan.getLocation());
        planShowDTO.setProgress(plan.getProgress());

        planShowDTO.setUserList(plan.getUserList().stream().map(user->{
            PlanUserDTO pld = new PlanUserDTO();
            pld.setId(user.getUser_id());
            pld.setUser_name(user.getUsername());
            return pld;
        }).toList());

        planShowDTO.setList_day(plan.getList_day().stream().map(day->{
            PlanDayDTO pdd = new PlanDayDTO();
            pdd.setDay_id(day.getDay_id());
            pdd.setDay_counter(day.getDay_counter());
            pdd.setDay_title(day.getDay_title());
            pdd.setBudget(pdd.getBudget());
            return pdd;
        }).toList());

        return planShowDTO;
    }
}
