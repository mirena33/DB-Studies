package softuni.wshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.wshop.model.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {
}
