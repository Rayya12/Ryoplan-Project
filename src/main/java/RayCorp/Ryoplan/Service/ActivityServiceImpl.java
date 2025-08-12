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
        activities.setActivity_name(activitiesDTO.getActivity_name());
        activities.setBudget(activitiesDTO.getBudget());
        activities.setJam_mulai(activitiesDTO.getJam_mulai());
        activities.setJam_selesai(activitiesDTO.getJam_selesai());

        Activities savedActivity = activitiesRepository.save(activities);

        ActivitiesDTO response = new ActivitiesDTO();
        response.setActivity_id(savedActivity.getActivity_id());
        response.setActivity_name(savedActivity.getActivity_name());
        response.setJam_mulai(savedActivity.getJam_mulai());
        response.setJam_selesai(savedActivity.getJam_selesai());
        response.setBudget(savedActivity.getBudget());

        return response;
    }

    @Override
    public ActivitiesDTO getActivityById(Long activity_id) {
        Activities searchedActivity = activitiesRepository.findById(activity_id).orElseThrow(()->new RuntimeException("Activity dengan id: "+activity_id+"Tidak ditemukan"));
        ActivitiesDTO response = new ActivitiesDTO();
        response.setActivity_id(searchedActivity.getActivity_id());
        response.setActivity_name(searchedActivity.getActivity_name());
        response.setJam_mulai(searchedActivity.getJam_mulai());
        response.setJam_selesai(searchedActivity.getJam_selesai());
        response.setBudget(searchedActivity.getBudget());

        return response;

    }

    @Override
    public List<ActivitiesDTO> listActivitiesByDay(Long day_id) {
        List<Activities> activities = activitiesRepository.findAllByDay_IdOrderByJamMulaiAsc(day_id);
        return activities.stream().map(a->new ActivitiesDTO(
                a.getActivity_id(),
                a.getActivity_name(),
                a.getBudget(),
                a.getJam_mulai(),
                a.getJam_selesai()
        )).toList();
    }

    @Override
    public ActivitiesDTO updateActivity(Long activityId, ActivitiesDTO dto) {
        Activities activities = activitiesRepository.findById(activityId).orElseThrow(()->new RuntimeException("Activity is not found"));
        activities.setActivity_name(dto.getActivity_name());
        activities.setBudget(dto.getBudget());
        activities.setJam_selesai(dto.getJam_selesai());
        activities.setJam_mulai(dto.getJam_mulai());

        Activities savedActivities = activitiesRepository.save(activities);
        ActivitiesDTO response = new ActivitiesDTO();
        response.setBudget(savedActivities.getBudget());
        response.setActivity_id(savedActivities.getActivity_id());
        response.setActivity_name(savedActivities.getActivity_name());
        response.setJam_mulai(savedActivities.getJam_mulai());
        response.setJam_selesai(savedActivities.getJam_selesai());

        return response;
    }

    @Override
    public void deleteActivity(Long activityId) {
        activitiesRepository.deleteById(activityId);
    }
}
