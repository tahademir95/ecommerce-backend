package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.ProductFeatureDetail;
import com.springboot.ecommerce.repository.ProductFeatureDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductFeatureDetailServiceImpl implements ProductFeatureDetailService{

    @Autowired
    private ProductFeatureDetailDao featureDetailDao;

    @Override
    public int createProductFeatureDetail(ProductFeatureDetail productFeatureDetail) {
        return featureDetailDao.addProductFeatureDetail(productFeatureDetail);
    }

    @Override
    public void removeProductFeatureDetailById(int id) {
        featureDetailDao.deleteProductFeatureDetail(id);
    }

    @Override
    public ProductFeatureDetail editProductFeatureDetail(ProductFeatureDetail productFeatureDetail) {
        featureDetailDao.updateProductFeatureDetail(productFeatureDetail);
        return productFeatureDetail;
    }

    @Override
    public List<ProductFeatureDetail> getProductFeatureDetailList() {
        return featureDetailDao.getProductFeatureDetails().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public ProductFeatureDetail findProductFeatureDetailById(int id) {
        return featureDetailDao.findProductFeatureDetailById(id);
    }
}
