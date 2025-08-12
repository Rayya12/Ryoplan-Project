package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.ActivitiesDTO;

import java.util.List;

public interface ActivityService {
    public ActivitiesDTO createActivities(Long day_id,ActivitiesDTO activitiesDTO);
    public ActivitiesDTO getActivityById(Long activity_id);
    public List<ActivitiesDTO> listActivitiesByDay(Long day_id);
    public ActivitiesDTO updateActivity(Long activityId,ActivitiesDTO dto);
    public void deleteActivity(Long activityId);



}
