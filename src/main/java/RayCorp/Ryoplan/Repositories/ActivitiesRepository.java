package RayCorp.Ryoplan.Repositories;

import RayCorp.Ryoplan.Model.Activities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities,Long> {
    public List<Activities> findAllByDayIdOrderByJamMulaiAsc(Long dayId);


}
