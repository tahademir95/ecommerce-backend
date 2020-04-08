package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pc_id;

    @Column
    private String categoryName;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "pc_id")
    private Set<ProductSubCategory> subCategories = new HashSet<>();


    public int getPc_id() {
        return pc_id;
    }

    public void setPc_id(int pc_id) {
        this.pc_id = pc_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<ProductSubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<ProductSubCategory> subCategories) {
        this.subCategories = subCategories;
    }

}
