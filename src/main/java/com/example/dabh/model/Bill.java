package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bill")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberProduct;
    private LocalDate date;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @ManyToMany
    @JoinTable(name = "bill_product",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;
}
