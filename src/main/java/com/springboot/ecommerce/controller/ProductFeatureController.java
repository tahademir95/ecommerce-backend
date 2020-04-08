package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.SubCategoryFeature;
import com.springboot.ecommerce.service.SubCategoryFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/subcategory-feature-api")
public class ProductFeatureController {

    @Autowired
    SubCategoryFeatureService featureService;

    @PostMapping(value = "/create-subcategory-feature", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewSubCategoryFeature(@RequestBody SubCategoryFeature feature, UriComponentsBuilder ucBuilder) {
        featureService.createSubCategoryFeature(feature);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/subcategory-feature/{id}").buildAndExpand(feature.getScf_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="/update-subcategory-feature", headers="Accept=application/json")
    public SubCategoryFeature updateSubCategoryFeature(@RequestBody SubCategoryFeature subCategoryFeature)
    {
        return  featureService.editSubCategoryFeature(subCategoryFeature);
    }

    @DeleteMapping(value="/delete-subcategory-feature/{id}", headers ="Accept=application/json")
    public ResponseEntity<SubCategoryFeature> deleteSubCategoryFeature(@PathVariable("id") int id){
        SubCategoryFeature subCategoryFeature = featureService.findSubCategoryFeatureById(id);
        if (subCategoryFeature == null) {
            return new ResponseEntity<SubCategoryFeature>(HttpStatus.NOT_FOUND);
        }
        featureService.removeSubCategoryFeatureById(id);
        return new ResponseEntity<SubCategoryFeature>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/subcategory-feature/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryFeature> getSubCategoryFeatureId(@PathVariable("id") int id) {

        SubCategoryFeature feature = featureService.findSubCategoryFeatureById(id);

        if (feature == null) {
            return new ResponseEntity<SubCategoryFeature>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SubCategoryFeature>(feature, HttpStatus.OK);
    }

    @GetMapping(value="/get-subcategory-feature-list", headers="Accept=application/json")
    public List<SubCategoryFeature> getAllSubCategoryFeatures() {
        return featureService.getSubCategoryFeatureList();
    }
}
