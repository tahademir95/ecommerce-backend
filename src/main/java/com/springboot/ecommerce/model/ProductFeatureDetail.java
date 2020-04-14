package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ProductFeatureDetail")
public class ProductFeatureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pfd_id;

    @Column
    private String productFeatureDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "scf_id")
    private SubCategoryFeature subCategoryFeature;

    @ManyToMany(mappedBy = "featureDetails", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public int getPfd_id() {
        return pfd_id;
    }

    public void setPfd_id(int pfd_id) {
        this.pfd_id = pfd_id;
    }

    public String getProductFeatureDetail() {
        return productFeatureDetail;
    }

    public void setProductFeatureDetail(String productFeatureDetail) {
        this.productFeatureDetail = productFeatureDetail;
    }

    public SubCategoryFeature getSubCategoryFeature() {
        return subCategoryFeature;
    }

    public void setSubCategoryFeature(SubCategoryFeature subCategoryFeature) {
        this.subCategoryFeature = subCategoryFeature;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
