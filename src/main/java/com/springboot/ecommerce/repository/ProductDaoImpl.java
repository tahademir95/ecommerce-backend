package com.springboot.ecommerce.repository;

import java.util.List;

import com.springboot.ecommerce.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements  ProductDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
        return product.getP_id();
    }

    @Override
    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = findProductById(id);
        session.delete(product);
    }

    @Override
    public int updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
        return product.getP_id();
    }

    @Override
    public Product findProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class, id);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Product> productList = session.createCriteria(Product.class).list();
        return productList;
    }
}
