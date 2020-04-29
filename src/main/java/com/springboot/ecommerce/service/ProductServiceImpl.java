package com.springboot.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;


    @Override
    public int createProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public void removeProductById(int id) {
        productDao.deleteProduct(id);
    }

    @Override
    public Product editProduct(Product product) {
        productDao.updateProduct(product);
        return product;
    }

    @Override
    public List<Product> getProductList() {
        return productDao.getProducts().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<Product> getProductListInTheSameSubCategory(int psc_id) {
        return productDao.getProductsInTheSameSubCategory(psc_id);
    }

    @Override
    public List<Product> getBrandNameAndCountOfProductListInTheSameSubcategory(int psc_id) {
        return productDao.getBrandNamesAndCountOfProductsInTheSameSubCategory(psc_id);
    }

    @Override
    public List<Product> getBrandNameAndCountOfProductListInTheSameCategory(int pc_id) {
        return productDao.getBrandNamesAndCountOfProductsInTheSameCategory(pc_id);
    }

    @Override
    public List<Product> getProductListInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost, List<String> brandNameList) {
        return productDao.getProductsInTheSameSubCategory(psc_id, minCost, maxCost, brandNameList);
    }

    @Override
    public List<Product> getProductListInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost) {
        return productDao.getProductsInTheSameSubCategory(psc_id, minCost, maxCost);
    }

    @Override
    public List<Product> getAllProductsUnderTheSameCategory(int pc_id) {
        return productDao.getProductsInTheSameCategory(pc_id);
    }
}
