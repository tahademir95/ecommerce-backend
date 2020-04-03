package com.springboot.ecommerce.service;

import java.util.List;

import com.springboot.ecommerce.model.ProductCategory;
import com.springboot.ecommerce.repository.ProductCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public int createProductCategory(ProductCategory productCategory) {
        return productCategoryDao.addCategory(productCategory);
    }

    @Override
    public ProductCategory editProductCategory(ProductCategory productCategory) {
        productCategoryDao.updateCategory(productCategory);
        return productCategory;
    }

    @Override
    public void removeCategoryById(int id) {
        productCategoryDao.deleteCategory(id);
    }

    @Override
    public List<ProductCategory> getCategoryList() {
        return productCategoryDao.getCategories();
    }

    @Override
    public ProductCategory findCategoryById(int id) {
        return productCategoryDao.findCategoryById(id);
    }
}
