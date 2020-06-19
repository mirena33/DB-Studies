package softuni.wshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.wshop.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
