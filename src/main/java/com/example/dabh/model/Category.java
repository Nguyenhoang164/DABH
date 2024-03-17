package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameCategory;
    private String descripsion;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users users;
}
