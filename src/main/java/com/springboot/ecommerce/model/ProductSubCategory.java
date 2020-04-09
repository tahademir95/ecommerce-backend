package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ProductSubCategory")
public class ProductSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int psc_id;

    @Column
    private String subCategoryName;

    @OneToMany(fetch = FetchType.EAGER)     //one subcategory can have multiple subcategory features
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "psc_id")
    private Set<SubCategoryFeature> subCategoryFeatures = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)     //multiple subcategories can have one category
    @JsonBackReference
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

    public Set<SubCategoryFeature> getSubCategoryFeatures() {
        return subCategoryFeatures;
    }

    public void setSubCategoryFeatures(Set<SubCategoryFeature> subCategoryFeatures) {
        this.subCategoryFeatures = subCategoryFeatures;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
