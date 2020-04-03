package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.ProductSubCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductSubCategoryDaoImpl implements ProductSubCategoryDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int addSubCategory(ProductSubCategory subCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.save(subCategory);
        return subCategory.getPsc_id();
    }

    @Override
    public void deleteSubCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductSubCategory subCategory = findSubCategoryById(id);
        session.delete(subCategory);
    }

    @Override
    public int updateSubCategory(ProductSubCategory subCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.update(subCategory);
        return subCategory.getPsc_id();
    }

    @Override
    public ProductSubCategory findSubCategoryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductSubCategory subCategory = (ProductSubCategory) session.get(ProductSubCategory.class, id);
        return subCategory;
    }

    @Override
    public List<ProductSubCategory> getSubCategories() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<ProductSubCategory> subCategoryList = session.createCriteria(ProductSubCategory.class).list();
        return subCategoryList;
    }
}
