package com.springboot.ecommerce.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="Product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int p_id;

    @Column(name = "p_name")
    private String productName;

    @Column(name = "p_brand")
    private String brandOfProduct;

    @Column(name = "p_fee")
    private int productFee;

    @ManyToOne
    @JoinColumn(name = "pc_id")
    @JsonIgnoreProperties({ "subCategories" })
    private ProductCategory productCategory;

    @ManyToOne
    @JsonIgnoreProperties(value = { "subCategoryFeatures"})
    @JoinColumn(name = "psc_id")
    private ProductSubCategory subCategory;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonManagedReference
    @JoinTable(name = "product_feature", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "p_id"),
               inverseJoinColumns = @JoinColumn(name = "product_feature_detail_temp_id", referencedColumnName = "pfd_id"))
    private Set<ProductFeatureDetail> featureDetails;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandOfProduct() {
        return brandOfProduct;
    }

    public void setBrandOfProduct(String brandOfProduct) {
        this.brandOfProduct = brandOfProduct;
    }

    public int getProductFee() {
        return productFee;
    }

    public void setProductFee(int productFee) {
        this.productFee = productFee;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductSubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Set<ProductFeatureDetail> getFeatureDetails() {
        return featureDetails;
    }

    public void setFeatureDetails(Set<ProductFeatureDetail> featureDetails) {
        this.featureDetails = featureDetails;
    }
}
