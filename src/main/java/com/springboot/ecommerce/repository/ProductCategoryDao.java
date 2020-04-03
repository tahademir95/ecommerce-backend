package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.ProductCategory;
import java.util.List;


public interface ProductCategoryDao {
    public int addCategory(ProductCategory productCategory);
    public void deleteCategory(int id);
    public int updateCategory(ProductCategory productCategory);
    public ProductCategory findCategoryById(int id);
    public List<ProductCategory> getCategories();
}
