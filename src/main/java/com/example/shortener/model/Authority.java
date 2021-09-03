package com.example.shortener.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int authority_id;
    @Column
    private String authority;
    @Transient
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    {
        users = new HashSet<>();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
