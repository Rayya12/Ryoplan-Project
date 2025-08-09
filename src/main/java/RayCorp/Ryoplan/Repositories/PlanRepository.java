package RayCorp.Ryoplan.Repositories;

import RayCorp.Ryoplan.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
