package RayCorp.Ryoplan.Mapper;

import RayCorp.Ryoplan.DTO.ActivitiesDTO;
import RayCorp.Ryoplan.DTO.DayDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Model.Day;

public class DayMapper {
    public static DayDTO toDayDTO(Day day){
        DayDTO dayDTO = new DayDTO();
        dayDTO.setDay_id(day.getId());
        dayDTO.setDay_counter(day.getDayCounter());
        dayDTO.setDay_title(day.getDayTitle());
        PlanShowDTO planShowDTO = PlanMapper.toPlanShowDTO(day.getPlan());
        dayDTO.setPlan(planShowDTO);
        dayDTO.setDescription(day.getDescription());
        dayDTO.setBiaya_hotel(day.getBiayaHotel());
        dayDTO.setList_aktivitas(day.getListAktivitas().stream().map(
                aktivitas->{
                    ActivitiesDTO activitiesDTO = new ActivitiesDTO();
                    activitiesDTO.setActivity_id(aktivitas.getId());
                    activitiesDTO.setActivity_name(aktivitas.getActivityName());
                    activitiesDTO.setBudget(aktivitas.getBudget());
                    activitiesDTO.setJam_mulai(aktivitas.getJamMulai());
                    activitiesDTO.setJam_selesai(aktivitas.getJamSelesai());
                    return activitiesDTO;
                }
        ).toList());
        return dayDTO;
    }
}
