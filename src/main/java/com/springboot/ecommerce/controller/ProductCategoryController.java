package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.ProductCategory;
import com.springboot.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/product-category-api")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService categoryService;

    @PostMapping(value = "/create-product-category", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProduct(@RequestBody ProductCategory productCategory, UriComponentsBuilder ucBuilder) {
        categoryService.createProductCategory(productCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product-category/{id}").buildAndExpand(productCategory.getPc_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="/update-product-category", headers="Accept=application/json")
    public ProductCategory updateProduct(@RequestBody ProductCategory productCategory)
    {
        return  categoryService.editProductCategory(productCategory);
    }

    @DeleteMapping(value="/delete-product-category/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductCategory> deleteProduct(@PathVariable("id") int id){
        ProductCategory productCategory = categoryService.findCategoryById(id);
        if (productCategory == null) {
            return new ResponseEntity<ProductCategory>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeCategoryById(id);
        return new ResponseEntity<ProductCategory>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/product-category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCategory> getTypeById(@PathVariable("id") int id) {

        ProductCategory productCategory = categoryService.findCategoryById(id);

        if (productCategory == null) {
            return new ResponseEntity<ProductCategory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }

    @GetMapping(value="/get-product-category-list", headers="Accept=application/json")
    public List<ProductCategory> getAllTypes() {
        return categoryService.getCategoryList();
    }
}
