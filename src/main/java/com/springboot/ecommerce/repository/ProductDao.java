package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.Product;

import java.util.List;

public interface ProductDao {
    public int addProduct(Product product);
    public void deleteProduct(int id);
    public int updateProduct(Product product);
    public Product findProductById(int id);
    public List<Product> getProducts();
    public List<Product> getBrandNamesAndCountOfProductsInTheSameSubCategory(int psc_id);
    public List<Product> getBrandNamesAndCountOfProductsInTheSameCategory(int pc_id);
    public List<Product> getProductsInTheSameSubCategory(int psc_id);
    public List<Product> getProductsInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost);
    public List<Product> getProductsInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost, List<String> brandNameList);
    public List<Product> getProductsInTheSameCategory(int pc_id);
}
