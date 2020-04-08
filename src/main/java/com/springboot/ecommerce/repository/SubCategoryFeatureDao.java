package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.SubCategoryFeature;

import java.util.List;

public interface SubCategoryFeatureDao {
    public int addSubCategoryFeature(SubCategoryFeature subCategoryFeature);
    public void deleteSubCategoryFeature(int id);
    public int updateSubCategoryFeature(SubCategoryFeature subCategoryFeature);
    public SubCategoryFeature findSubCategoryFeatureById(int id);
    public List<SubCategoryFeature> getSubCategoryFeatures();
}
