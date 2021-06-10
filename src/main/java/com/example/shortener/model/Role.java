package com.example.shortener.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String roleName;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
