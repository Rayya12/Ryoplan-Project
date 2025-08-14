package RayCorp.Ryoplan.Mapper;

import RayCorp.Ryoplan.DTO.PlanDayDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.DTO.PlanUserDTO;
import RayCorp.Ryoplan.Model.Plan;

public class PlanMapper {
    public static PlanShowDTO toPlanShowDTO(Plan plan){
        PlanShowDTO planShowDTO = new PlanShowDTO();
        planShowDTO.setPlan_id(plan.getId());
        planShowDTO.setPlan_name(plan.getPlanName());
        planShowDTO.setDescription(plan.getDescription());
        planShowDTO.setBudget(plan.getBudgetPlan());
        planShowDTO.setAvailable_counter(plan.getAvailableCounter());
        planShowDTO.setTanggal_mulai(plan.getTanggalMulai());
        planShowDTO.setTanggal_selesai(plan.getTanggalSelesai());
        planShowDTO.setLocation(plan.getLocation());
        planShowDTO.setProgress(plan.getProgress());

        planShowDTO.setUserList(plan.getUserList().stream().map(user->{
            PlanUserDTO pld = new PlanUserDTO();
            pld.setId(user.getId());
            pld.setUser_name(user.getUsername());
            return pld;
        }).toList());

        planShowDTO.setList_day(plan.getListDay().stream().map(day->{
            PlanDayDTO pdd = new PlanDayDTO();
            pdd.setDay_id(day.getId());
            pdd.setDay_counter(day.getDayCounter());
            pdd.setDay_title(day.getDayTitle());
            pdd.setBudget(pdd.getBudget());
            return pdd;
        }).toList());

        return planShowDTO;
    }

}
