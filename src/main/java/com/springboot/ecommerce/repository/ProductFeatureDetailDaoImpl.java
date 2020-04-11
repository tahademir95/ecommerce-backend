package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.ProductFeatureDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductFeatureDetailDaoImpl implements ProductFeatureDetailDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addProductFeatureDetail(ProductFeatureDetail productFeatureDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productFeatureDetail);
        return productFeatureDetail.getPfd_id();
    }

    @Override
    public void deleteProductFeatureDetail(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductFeatureDetail productFeatureDetail = findProductFeatureDetailById(id);
        session.delete(productFeatureDetail);
    }

    @Override
    public int updateProductFeatureDetail(ProductFeatureDetail productFeatureDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productFeatureDetail);
        return productFeatureDetail.getPfd_id();
    }

    @Override
    public ProductFeatureDetail findProductFeatureDetailById(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductFeatureDetail productFeatureDetail = (ProductFeatureDetail) session.get(ProductFeatureDetail.class, id);
        return productFeatureDetail;
    }

    @Override
    public List<ProductFeatureDetail> getProductFeatureDetails() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from com.springboot.ecommerce.model.ProductFeatureDetail").list();
    }
}
