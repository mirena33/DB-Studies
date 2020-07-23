package softuni.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.hateoas.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
