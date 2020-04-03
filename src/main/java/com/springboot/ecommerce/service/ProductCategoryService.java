package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    public int createProductCategory(ProductCategory productCategory);
    public ProductCategory editProductCategory(ProductCategory productCategory);
    public void removeCategoryById(int id);
    public List<ProductCategory> getCategoryList();
    public ProductCategory findCategoryById(int id);
}
