package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int addCategory(ProductCategory productCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productCategory);
        return productCategory.getPc_id();
    }

    @Override
    public void deleteCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductCategory productCategory = findCategoryById(id);
        session.delete(productCategory);
    }

    @Override
    public int updateCategory(ProductCategory productCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productCategory);
        return productCategory.getPc_id();
    }

    @Override
    public ProductCategory findCategoryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductCategory productCategory = (ProductCategory) session.get(ProductCategory.class, id);
        return productCategory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductCategory> getCategories() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from com.springboot.ecommerce.model.ProductCategory").list();
    }
}
