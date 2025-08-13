package RayCorp.Ryoplan.Repositories;

import RayCorp.Ryoplan.Model.Activities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivitiesRepository extends JpaRepository<Activities,Long> {
    public List<Activities> findAllByDay_IdOrderByJamMulaiAsc(Long dayId);

}
