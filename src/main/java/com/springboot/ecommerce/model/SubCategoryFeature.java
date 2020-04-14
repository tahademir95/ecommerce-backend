package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="SubCategoryFeature")
public class SubCategoryFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scf_id;

    @Column
    private String subCategoryFeatureName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "psc_id")
    private ProductSubCategory subCategory;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "scf_id")
    private Set<ProductFeatureDetail> productFeatureDetails = new HashSet<>();

    public int getScf_id() {
        return scf_id;
    }

    public void setScf_id(int sf_id) {
        this.scf_id = sf_id;
    }

    public String getSubCategoryFeatureName() {
        return subCategoryFeatureName;
    }

    public void setSubCategoryFeatureName(String subCategoryFeatureName) {
        this.subCategoryFeatureName = subCategoryFeatureName;
    }

    public ProductSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductSubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Set<ProductFeatureDetail> getProductFeatureDetails() {
        return productFeatureDetails;
    }

    public void setProductFeatureDetails(Set<ProductFeatureDetail> productFeatureDetails) {
        this.productFeatureDetails = productFeatureDetails;
    }
}
