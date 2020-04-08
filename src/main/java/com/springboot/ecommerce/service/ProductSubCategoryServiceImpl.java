package com.springboot.ecommerce.service;


import com.springboot.ecommerce.model.ProductSubCategory;
import com.springboot.ecommerce.repository.ProductSubCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductSubCategoryServiceImpl implements ProductSubCategoryService{

    @Autowired
    ProductSubCategoryDao subCategoryDao;

    @Override
    public int createSubCategory(ProductSubCategory subCategory) {
        return subCategoryDao.addSubCategory(subCategory);
    }

    @Override
    public ProductSubCategory editSubCategory(ProductSubCategory subCategory) {
        subCategoryDao.updateSubCategory(subCategory);
        return subCategory;
    }

    @Override
    public void removeSubCategoryById(int id) {
        subCategoryDao.deleteSubCategory(id);
    }

    @Override
    public List<ProductSubCategory> getSubCategoryList() {
        return subCategoryDao.getSubCategories().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public ProductSubCategory findSubCategoryById(int id) {
        return subCategoryDao.findSubCategoryById(id);
    }
}
