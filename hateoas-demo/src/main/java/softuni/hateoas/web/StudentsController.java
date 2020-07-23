package softuni.hateoas.web;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.hateoas.dtos.OrderDTO;
import softuni.hateoas.model.Student;
import softuni.hateoas.repository.StudentRepository;

import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentRepository studentRepository;

    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Student>>> getAllStudents() {
        List<EntityModel<Student>> allStudents =
                this.studentRepository
                        .findAll()
                        .stream()
                        .map(s -> EntityModel.of(s, getStudentLinks(s)))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(allStudents, linkTo(methodOn(StudentsController.class).getAllStudents()).withSelfRel()));
    }

//    @GetMapping("/{id}/orders")
//    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getAllOrdersByStudentId(
//            @PathVariable (name = "id") Long studentId){
//
//    }

    private Link[] getStudentLinks(Student student) {
        //todo
        return new Link[0];
    }
}
