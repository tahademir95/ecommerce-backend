package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.ProductFeatureDetail;
import java.util.List;

public interface ProductFeatureDetailDao {
    public int addProductFeatureDetail(ProductFeatureDetail productFeatureDetail);
    public void deleteProductFeatureDetail(int id);
    public int updateProductFeatureDetail(ProductFeatureDetail productFeatureDetail);
    public ProductFeatureDetail findProductFeatureDetailById(int id);
    public List<ProductFeatureDetail> getProductFeatureDetails();
}
