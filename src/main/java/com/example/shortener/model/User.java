package com.example.shortener.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int user_id;
    @Column
    private String username;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = @JoinColumn(name = "USERS_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITIES_ID")
    )
    private Set<Authority> authorities;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Link> links;

    {
        authorities = new HashSet<>();
        links = new LinkedList<>();
    }
}
