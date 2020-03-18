package com.softuni.demo.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "size")
    private double size;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    private Set<User> users;

    @ManyToMany(mappedBy = "orderedGames", targetEntity = Order.class)
    private Set<Order> orders;
}
