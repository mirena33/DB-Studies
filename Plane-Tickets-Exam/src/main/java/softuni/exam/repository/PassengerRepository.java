package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Passenger;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger getByEmail(String email);

    @Query("SELECT p FROM Passenger AS p ORDER BY p.tickets.size DESC, p.email ASC")
    List<Passenger> findAllOrderedByTicketsDESCAndEmailASC();
}