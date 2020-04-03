package com.springboot.ecommerce.service;

import java.util.List;

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
        return productDao.getProducts();
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }
}
