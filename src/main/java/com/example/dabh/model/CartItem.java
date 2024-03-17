package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "id_Customer")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "id_Product")
    private Product product;
}
