package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Controller;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int count;
    private double price;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;
}
