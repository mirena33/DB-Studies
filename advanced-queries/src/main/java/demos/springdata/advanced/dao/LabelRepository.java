package demos.springdata.advanced.dao;

import demos.springdata.advanced.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Label findOneById(Long id);
}
