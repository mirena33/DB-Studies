package com.softuni.demo.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToMany
    @JoinTable(name = "orders_ordered_games",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ordered_games_id", referencedColumnName = "id"))
    private Set<Game> orderedGames;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private User user;

}
