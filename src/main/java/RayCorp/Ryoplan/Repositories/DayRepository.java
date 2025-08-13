package RayCorp.Ryoplan.Repositories;

import RayCorp.Ryoplan.Model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DayRepository extends JpaRepository<Day,Long> {
    public List<Day> findAllByPlan_idOrderByDay_counterAsc(Long plan_id);
    public Day findByDay_counterAndPlan_id(Integer day_counter,Long plan_id);
    public List<Day> findAllByPlan_idAndDay_counterGreaterThanOrderByDay_counterAsc(Long plan_id,Integer day_counter);
}
