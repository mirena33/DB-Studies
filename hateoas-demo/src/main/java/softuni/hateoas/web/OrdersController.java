package softuni.hateoas.web;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.hateoas.dtos.OrderDTO;
import softuni.hateoas.model.Course;
import softuni.hateoas.model.Order;
import softuni.hateoas.model.Student;
import softuni.hateoas.repository.CourseRepository;
import softuni.hateoas.repository.OrderRepository;
import softuni.hateoas.repository.StudentRepository;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;

    public OrdersController(StudentRepository studentRepository, OrderRepository orderRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderDTO>> createOrder(@RequestBody OrderDTO orderDTO) {

        Student student = this.studentRepository.getOne(orderDTO.getStudentId());
        Course course = this.courseRepository.getOne(orderDTO.getCourseId());

        Order newOrder = new Order();
        newOrder.setStudent(student);
        newOrder.setCourse(course);

        newOrder = this.orderRepository.save(newOrder);

        return ResponseEntity.ok(EntityModel.of(OrderDTO.asDTO(newOrder)));
    }
}
