package softuni.hateoas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.hateoas.model.Course;
import softuni.hateoas.model.Order;
import softuni.hateoas.model.Student;
import softuni.hateoas.repository.CourseRepository;
import softuni.hateoas.repository.OrderRepository;
import softuni.hateoas.repository.StudentRepository;

@Component
public class HateoasApplicationInit implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;

    public HateoasApplicationInit(StudentRepository studentRepository, OrderRepository orderRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Student student = new Student();
        student.setName("Ivan");
        student.setAge(22);

        student = studentRepository.save(student);

        Course springDataCourse = new Course();
        springDataCourse.setName("Spring data");
        springDataCourse.setPrice(100.00);
        springDataCourse.setEnabled(true);

        springDataCourse = courseRepository.save(springDataCourse);

        Course springBatchCourse = new Course();
        springDataCourse.setName("Spring batch");
        springDataCourse.setPrice(150.00);
        springDataCourse.setEnabled(false);

        springBatchCourse = courseRepository.save(springBatchCourse);

        Order studentSpringData = new Order();
        studentSpringData.setCourse(springDataCourse);
        studentSpringData.setStudent(student);

        orderRepository.save(studentSpringData);

    }
}
