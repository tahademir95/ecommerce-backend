package com.springboot.ecommerce.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "psc_id")
    private ProductSubCategory subCategory;

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
}
