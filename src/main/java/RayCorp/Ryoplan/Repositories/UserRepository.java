package RayCorp.Ryoplan.Repositories;

import RayCorp.Ryoplan.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
