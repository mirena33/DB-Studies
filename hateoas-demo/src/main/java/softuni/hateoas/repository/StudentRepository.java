package softuni.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.hateoas.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
