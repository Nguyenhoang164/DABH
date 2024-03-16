package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.service.spi.InjectService;

import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameProduct;
    private String price;
    private String type;
    private String descripsion;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToMany
    @JoinTable(name = "product_bill")
    private Set<Bill> bills;
}
