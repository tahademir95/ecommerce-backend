package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.ProductSubCategory;
import com.springboot.ecommerce.service.ProductSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/product-subcategory-api")
public class ProductSubCategoryController {

    @Autowired
    ProductSubCategoryService subCategoryService;

    @PostMapping(value = "/create-product-subcategory", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewSubCategory(@RequestBody ProductSubCategory subCategory, UriComponentsBuilder ucBuilder) {
        subCategoryService.createSubCategory(subCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product-subcategory/{id}").buildAndExpand(subCategory.getPsc_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="/update-product-subcategory", headers="Accept=application/json")
    public ProductSubCategory updateSubCategory(@RequestBody ProductSubCategory subCategory)
    {
        return  subCategoryService.editSubCategory(subCategory);
    }

    @DeleteMapping(value="/delete-product-subcategory/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductSubCategory> deleteSubCategory(@PathVariable("id") int id){
        ProductSubCategory subCategory = subCategoryService.findSubCategoryById(id);
        if (subCategory == null) {
            return new ResponseEntity<ProductSubCategory>(HttpStatus.NOT_FOUND);
        }
        subCategoryService.removeSubCategoryById(id);
        return new ResponseEntity<ProductSubCategory>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/product-subcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductSubCategory> getSubCategoryById(@PathVariable("id") int id) {

        ProductSubCategory subCategory = subCategoryService.findSubCategoryById(id);

        if (subCategory == null) {
            return new ResponseEntity<ProductSubCategory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductSubCategory>(subCategory, HttpStatus.OK);
    }

    @GetMapping(value="/get-product-subcategory-list", headers="Accept=application/json")
    public List<ProductSubCategory> getAllSubCategories() {
        return subCategoryService.getSubCategoryList();
    }
}
