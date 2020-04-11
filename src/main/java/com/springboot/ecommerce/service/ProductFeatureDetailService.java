package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.ProductFeatureDetail;
import java.util.List;

public interface ProductFeatureDetailService {
    public int createProductFeatureDetail(ProductFeatureDetail productFeatureDetail);
    public void removeProductFeatureDetailById(int id);
    public ProductFeatureDetail editProductFeatureDetail(ProductFeatureDetail productFeatureDetail);
    public List<ProductFeatureDetail> getProductFeatureDetailList();
    public ProductFeatureDetail findProductFeatureDetailById(int id);
}
