package com.example.shortener.model;

import com.example.shortener.jsonViews.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @JsonView(UserView.UserLoginView.class)
    private String login;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
