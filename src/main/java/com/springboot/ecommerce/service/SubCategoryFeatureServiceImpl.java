package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.SubCategoryFeature;
import com.springboot.ecommerce.repository.SubCategoryFeatureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubCategoryFeatureServiceImpl implements SubCategoryFeatureService {

    @Autowired
    SubCategoryFeatureDao featureDao;

    @Override
    public int createSubCategoryFeature(SubCategoryFeature subCategoryFeature) {
        return featureDao.addSubCategoryFeature(subCategoryFeature);
    }

    @Override
    public SubCategoryFeature editSubCategoryFeature(SubCategoryFeature subCategoryFeature) {
        featureDao.updateSubCategoryFeature(subCategoryFeature);
        return subCategoryFeature;
    }

    @Override
    public void removeSubCategoryFeatureById(int id) {
        featureDao.deleteSubCategoryFeature(id);
    }

    @Override
    public List<SubCategoryFeature> getSubCategoryFeatureList() {
        return featureDao.getSubCategoryFeatures();
    }

    @Override
    public SubCategoryFeature findSubCategoryFeatureById(int id) {
        return featureDao.findSubCategoryFeatureById(id);
    }
}
