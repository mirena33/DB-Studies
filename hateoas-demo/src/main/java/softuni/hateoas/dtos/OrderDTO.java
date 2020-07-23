package softuni.hateoas.dtos;

import softuni.hateoas.model.Order;

public class OrderDTO {

    private Long id;
    private Long studentId;
    private Long courseId;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public static OrderDTO asDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCourseId(order.getCourse().getId());
        orderDTO.setStudentId(order.getStudent().getId());
        return orderDTO;
    }
}
