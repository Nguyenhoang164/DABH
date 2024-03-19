package com.example.dabh.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private int id;
    private String nameProduct;
    private String price;
    private String type;
    private String descripsion;
    private boolean status;
    private MultipartFile picture;
    private int idCategory;

    public ProductForm(int id, String nameProduct, String price, String type, String descripsion, boolean status, MultipartFile picture, int idCategory) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.type = type;
        this.descripsion = descripsion;
        this.status = status;
        this.picture = picture;
        this.idCategory = idCategory;
    }

    public ProductForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
