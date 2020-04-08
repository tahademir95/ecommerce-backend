package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.SubCategoryFeature;

import java.util.List;

public interface SubCategoryFeatureService {
    public int createSubCategoryFeature(SubCategoryFeature subCategoryFeature);
    public SubCategoryFeature editSubCategoryFeature(SubCategoryFeature subCategoryFeature);
    public void removeSubCategoryFeatureById(int id);
    public List<SubCategoryFeature> getSubCategoryFeatureList();
    public SubCategoryFeature findSubCategoryFeatureById(int id);
}
