package com.xmldemo.data.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    private long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
