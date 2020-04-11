package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.ProductFeatureDetail;
import com.springboot.ecommerce.service.ProductFeatureDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/product-feature-detail-api")
public class ProductFeatureDetailController {
    @Autowired
    ProductFeatureDetailService featureDetailService;

    @PostMapping(value = "/create-product-feature-detail", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProductFeatureDetail(@RequestBody ProductFeatureDetail productFeatureDetail, UriComponentsBuilder ucBuilder) {
        featureDetailService.createProductFeatureDetail(productFeatureDetail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product-product-feature-detail/{id}").buildAndExpand(productFeatureDetail.getPfd_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="/update-product-feature-detail", headers="Accept=application/json")
    public ProductFeatureDetail updateProductFeatureDetail(@RequestBody ProductFeatureDetail productFeatureDetail)
    {
        return  featureDetailService.editProductFeatureDetail(productFeatureDetail);
    }

    @DeleteMapping(value="/delete-product-feature-detail/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductFeatureDetail> deleteProductFeatureDetail(@PathVariable("id") int id){
        ProductFeatureDetail featureDetail = featureDetailService.findProductFeatureDetailById(id);
        if (featureDetail == null) {
            return new ResponseEntity<ProductFeatureDetail>(HttpStatus.NOT_FOUND);
        }
        featureDetailService.removeProductFeatureDetailById(id);
        return new ResponseEntity<ProductFeatureDetail>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/product-feature-detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductFeatureDetail> getProductFeatureDetailById(@PathVariable("id") int id) {

        ProductFeatureDetail featureDetail = featureDetailService.findProductFeatureDetailById(id);

        if (featureDetail == null) {
            return new ResponseEntity<ProductFeatureDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductFeatureDetail>(featureDetail, HttpStatus.OK);
    }

    @GetMapping(value="/get-product-feature-detail-list", headers="Accept=application/json")
    public List<ProductFeatureDetail> getAllProductFeatureDetails() {
        return featureDetailService.getProductFeatureDetailList();
    }
}
