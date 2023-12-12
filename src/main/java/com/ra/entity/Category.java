package com.ra.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
@Entity
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  int id;
    private  String categoryName;
    private  Boolean status;
    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER )
    @Transient
    private List<Product> products;
    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
