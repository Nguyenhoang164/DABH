package com.example.dabh.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
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
    private String picture;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToMany(mappedBy = "products")
    private Set<Bill> bills;

    public Product(int id, String nameProduct, String price, String type, String descripsion, boolean status, String filename) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.type = type;
        this.descripsion = descripsion;
        this.status = status;
        this.picture = filename;
    }

    public Product() {

    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
