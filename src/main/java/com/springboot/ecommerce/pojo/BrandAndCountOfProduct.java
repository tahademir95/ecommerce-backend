package com.springboot.ecommerce.pojo;

public class BrandAndCountOfProduct {
    private String brand;
    private long countOfProduct;

    public BrandAndCountOfProduct(String brand, long countOfProduct) {
        this.brand = brand;
        this.countOfProduct = countOfProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(long countOfProduct) {
        this.countOfProduct = countOfProduct;
    }
}
