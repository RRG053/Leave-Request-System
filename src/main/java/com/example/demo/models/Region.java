package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "name")
    private String Name;

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
