package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.ActivitiesDTO;
import RayCorp.Ryoplan.Model.Activities;
import RayCorp.Ryoplan.Model.Day;
import RayCorp.Ryoplan.Repositories.ActivitiesRepository;
import RayCorp.Ryoplan.Repositories.DayRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private  ActivitiesRepository activitiesRepository;

    @Autowired
    private DayRepository dayRepository;

    @Override
    public ActivitiesDTO createActivities(Long day_id, ActivitiesDTO activitiesDTO) {
        Day day = dayRepository.findById(day_id).orElseThrow(()->new RuntimeException("Day with id - "+day_id+" not found"));

        Activities activities = new Activities();
        activities.setDay(day);
        activities.setActivityName(activitiesDTO.getActivity_name());
        activities.setBudget(activitiesDTO.getBudget());
        activities.setJamMulai(activitiesDTO.getJam_mulai());
        activities.setJamSelesai(activitiesDTO.getJam_selesai());

        day.getListAktivitas().add(activities);
        Activities savedActivity = activitiesRepository.save(activities);

        ActivitiesDTO response = new ActivitiesDTO();
        response.setActivity_id(savedActivity.getId());
        response.setActivity_name(savedActivity.getActivityName());
        response.setJam_mulai(savedActivity.getJamMulai());
        response.setJam_selesai(savedActivity.getJamSelesai());
        response.setBudget(savedActivity.getBudget());

        return response;
    }

    @Override
    public ActivitiesDTO getActivityById(Long activity_id) {
        Activities searchedActivity = activitiesRepository.findById(activity_id).orElseThrow(()->new RuntimeException("Activity dengan id: "+activity_id+"Tidak ditemukan"));
        ActivitiesDTO response = new ActivitiesDTO();
        response.setActivity_id(searchedActivity.getId());
        response.setActivity_name(searchedActivity.getActivityName());
        response.setJam_mulai(searchedActivity.getJamMulai());
        response.setJam_selesai(searchedActivity.getJamSelesai());
        response.setBudget(searchedActivity.getBudget());

        return response;

    }

    @Override
    public List<ActivitiesDTO> listActivitiesByDay(Long day_id) {
        List<Activities> activities = activitiesRepository.findAllByDayIdOrderByJamMulaiAsc(day_id);
        return activities.stream().map(a->new ActivitiesDTO(
                a.getId(),
                a.getActivityName(),
                a.getBudget(),
                a.getJamMulai(),
                a.getJamSelesai()
        )).toList();
    }

    @Override
    public ActivitiesDTO updateActivity(Long activityId, ActivitiesDTO dto) {
        Activities activities = activitiesRepository.findById(activityId).orElseThrow(()->new RuntimeException("Activity is not found"));
        activities.setActivityName(dto.getActivity_name());
        activities.setBudget(dto.getBudget());
        activities.setJamSelesai(dto.getJam_selesai());
        activities.setJamMulai(dto.getJam_mulai());

        Activities savedActivities = activitiesRepository.save(activities);
        ActivitiesDTO response = new ActivitiesDTO();
        response.setBudget(savedActivities.getBudget());
        response.setActivity_id(savedActivities.getId());
        response.setActivity_name(savedActivities.getActivityName());
        response.setJam_mulai(savedActivities.getJamMulai());
        response.setJam_selesai(savedActivities.getJamSelesai());

        return response;
    }

    @Override
    public void deleteActivity(Long activityId) {
        activitiesRepository.deleteById(activityId);
    }
}
