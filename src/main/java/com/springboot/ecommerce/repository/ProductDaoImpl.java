package com.springboot.ecommerce.repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.model.ProductSubCategory;
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
        return session.createQuery("from com.springboot.ecommerce.model.Product").list();
    }

    @Override
    public List<Product> getProductsInTheSameSubCategory(int psc_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from com.springboot.ecommerce.model.Product p  inner join com.springboot.ecommerce.model.ProductSubCategory s on p.subCategory.psc_id=s.psc_id where s.psc_id in :psc_id_temp").setParameter("psc_id_temp", psc_id).list();
    }

    @Override
    public List<Product> getProductsInTheSameCategory(int pc_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from com.springboot.ecommerce.model.Product p  inner join com.springboot.ecommerce.model.ProductCategory c on p.productCategory.pc_id=c.pc_id where c.pc_id in :pc_id_temp").setParameter("pc_id_temp", pc_id).list();
    }
}
