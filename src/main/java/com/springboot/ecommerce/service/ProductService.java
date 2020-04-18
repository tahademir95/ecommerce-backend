package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    public int createProduct(Product product);
    public void removeProductById(int id);
    public Product editProduct(Product product);
    public List<Product> getProductList();
    public Product findProductById(int id);
    public List<Product> getProductListInTheSameSubCategory(int psc_id);
    public List<Product> getProductListInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost, List<String> brandNameList);
    public List<Product> getProductListInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost);
    public List<Product> getAllProductsUnderTheSameCategory(int pc_id);
}
