package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameUser;
    private String email;
    private String phone;
    private String address;
    private String password;
    @Getter
    @ManyToMany
    @JoinTable(name = "users_roles")
    private Set<Role> role;
}
