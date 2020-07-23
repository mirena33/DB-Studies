package softuni.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.hateoas.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
