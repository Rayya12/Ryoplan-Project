package RayCorp.Ryoplan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RayCorp.Ryoplan.DTO.ActivitiesDTO;
import RayCorp.Ryoplan.Service.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {

    private final ActivityService activityService;

    public ActivitiesController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/create/{dayId}")
    public ResponseEntity<ActivitiesDTO> createActivity(@PathVariable Long dayId,@RequestBody ActivitiesDTO activityDTO) {
        ActivitiesDTO activitiesDTO =  activityService.createActivities(dayId, activityDTO);
        return ResponseEntity.ok(activitiesDTO);
    }


}
