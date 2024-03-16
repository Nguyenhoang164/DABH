package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameRole;
    @ManyToMany(mappedBy = "role")
    private Set<Users> users;
}
