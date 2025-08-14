package RayCorp.Ryoplan.Repositories;

import RayCorp.Ryoplan.Model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day,Long> {
    public List<Day> findAllByPlanIdOrderByDayCounterAsc(Long plan_id);
    public Day findByDayCounterAndPlanId(Integer day_counter,Long plan_id);
    public List<Day> findAllByPlanIdAndDayCounterGreaterThanOrderByDayCounterAsc(Long plan_id,Integer day_counter);
}
