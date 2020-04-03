package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "EcommerceProductSubCategory")
public class ProductSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int psc_id;

    @Column
    private String subCategoryName;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pc_id")
    private ProductCategory productCategory;

    public int getPsc_id() {
        return psc_id;
    }

    public void setPsc_id(int psc_id) {
        this.psc_id = psc_id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
