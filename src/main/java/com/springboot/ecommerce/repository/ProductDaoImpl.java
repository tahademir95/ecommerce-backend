package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @SuppressWarnings("unchecked")
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from com.springboot.ecommerce.model.Product").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getBrandNamesAndCountOfProductsInTheSameSubCategory(int psc_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select new com.springboot.ecommerce.pojo.BrandAndCountOfProduct(p.brandOfProduct, count(p.p_id)) " +
                                      "from com.springboot.ecommerce.model.Product p  " +
                                      "where p.subCategory.psc_id in :psc_id_temp " +
                                      "group by p.brandOfProduct")
                .setParameter("psc_id_temp", psc_id).getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getBrandNamesAndCountOfProductsInTheSameCategory(int pc_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select new com.springboot.ecommerce.pojo.BrandAndCountOfProduct(p.brandOfProduct, count(p.p_id)) " +
                "from com.springboot.ecommerce.model.Product p  " +
                "where p.productCategory.pc_id in :pc_id_temp " +
                "group by p.brandOfProduct")
                .setParameter("pc_id_temp", pc_id).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsInTheSameSubCategory(int psc_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from com.springboot.ecommerce.model.Product p  " +
                                      "inner join com.springboot.ecommerce.model.ProductSubCategory s " +
                                      "on p.subCategory.psc_id=s.psc_id " +
                                      "where s.psc_id in :psc_id_temp")
                .setParameter("psc_id_temp", psc_id).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost, List<String> brandNameList) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from com.springboot.ecommerce.model.Product p  " +
                                      "inner join com.springboot.ecommerce.model.ProductSubCategory s " +
                                      "on p.subCategory.psc_id=s.psc_id " +
                                      "where s.psc_id in :psc_id_temp " +
                                      "and p.productFee > :minCost_temp " +
                                      "and p.productFee < :maxCost_temp " +
                                      "and p.brandOfProduct in :brandNameList_temp")
                .setParameter("psc_id_temp", psc_id)
                .setParameter("minCost_temp", minCost)
                .setParameter("maxCost_temp", maxCost)
                .setParameterList("brandNameList_temp", brandNameList)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsInTheSameSubCategory(int psc_id, Integer minCost, Integer maxCost) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from com.springboot.ecommerce.model.Product p  " +
                                      "inner join com.springboot.ecommerce.model.ProductSubCategory s " +
                                      "on p.subCategory.psc_id=s.psc_id " +
                                      "where s.psc_id in :psc_id_temp " +
                                      "and p.productFee > :minCost_temp " +
                                      "and p.productFee < :maxCost_temp")
                .setParameter("psc_id_temp", psc_id)
                .setParameter("minCost_temp", minCost)
                .setParameter("maxCost_temp", maxCost)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsInTheSameCategory(int pc_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from com.springboot.ecommerce.model.Product p  " +
                                      "inner join com.springboot.ecommerce.model.ProductCategory c " +
                                      "on p.productCategory.pc_id=c.pc_id " +
                                      "where c.pc_id in :pc_id_temp")
                .setParameter("pc_id_temp", pc_id).list();
    }

}
