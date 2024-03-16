package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

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
    private Date date;
    private boolean status;
    @OneToOne
    @JoinColumn(name = "id_user")
    private Users users;
    @OneToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @ManyToMany(mappedBy = "bills")
    private Set<Product> products;
}
