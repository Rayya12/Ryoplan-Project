package RayCorp.Ryoplan.Mapper;

import RayCorp.Ryoplan.DTO.ActivitiesDTO;
import RayCorp.Ryoplan.DTO.DayDTO;
import RayCorp.Ryoplan.DTO.PlanShowDTO;
import RayCorp.Ryoplan.Model.Day;

public class DayMapper {
    public static DayDTO toDayDTO(Day day){
        DayDTO dayDTO = new DayDTO();
        dayDTO.setDay_id(day.getDay_id());
        dayDTO.setDay_counter(day.getDay_counter());
        dayDTO.setDay_title(day.getDay_title());
        PlanShowDTO planShowDTO = PlanMapper.toPlanShowDTO(day.getPlan());
        dayDTO.setPlan(planShowDTO);
        dayDTO.setDescription(day.getDescription());
        dayDTO.setBiaya_hotel(day.getBiaya_hotel());
        dayDTO.setList_aktivitas(day.getListAktivitas().stream().map(
                aktivitas->{
                    ActivitiesDTO activitiesDTO = new ActivitiesDTO();
                    activitiesDTO.setActivity_id(aktivitas.getActivity_id());
                    activitiesDTO.setActivity_name(aktivitas.getActivity_name());
                    activitiesDTO.setBudget(aktivitas.getBudget());
                    activitiesDTO.setJam_mulai(aktivitas.getJam_mulai());
                    activitiesDTO.setJam_selesai(aktivitas.getJam_selesai());
                    return activitiesDTO;
                }
        ).toList());
        return dayDTO;
    }

    public static Day fromDTO()
}
