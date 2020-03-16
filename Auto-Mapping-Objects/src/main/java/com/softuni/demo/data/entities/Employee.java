package com.softuni.demo.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "entities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private double salary;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City cityName;
}
