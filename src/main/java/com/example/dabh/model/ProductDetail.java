package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String size;
    private String parameter;
    private String descripsion;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
}
