package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/product-api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/create-product", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProduct(@RequestBody Product  product, UriComponentsBuilder ucBuilder){
        productService.createProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getP_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="/update-product", headers="Accept=application/json")
    public Product updateProduct(@RequestBody Product product)
    {
        return  productService.editProduct(product);
    }

    @DeleteMapping(value="/delete-product/{id}", headers ="Accept=application/json")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id){
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productService.removeProductById(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {

        Product product = productService.findProductById(id);

        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping(value="/get-product-list", headers="Accept=application/json")
    public List<Product> getAllProducts() {
        return productService.getProductList();
    }

    @GetMapping(value="/get-product-list-in-the-same-subcategory/{psc_id}", headers="Accept=application/json")
    public List<Product> getAllProductsInTheSameSubCategory(@PathVariable("psc_id") int psc_id,
                                                            @RequestParam(value = "minCost", required = false, defaultValue = "0") Integer minCost,
                                                            @RequestParam(value = "maxCost", required = false, defaultValue = "99999") Integer maxCost,
                                                            @RequestParam(value = "brandName", required = false) List<String> brandNameList) {

        if (((minCost != null && maxCost != null) &&  brandNameList != null )){
            return (List<Product>) productService.getProductListInTheSameSubCategory(psc_id, minCost, maxCost, brandNameList);
        }
        else {
            return (List<Product>) productService.getProductListInTheSameSubCategory(psc_id, minCost, maxCost);
        }
    }

    @GetMapping(value="/get-product-list-in-the-same-category/{pc_id}", headers="Accept=application/json")
    public List<Product> getAllProductsUnderTheSameCategory(@PathVariable("pc_id") int pc_id) {
        return (List<Product>) productService.getAllProductsUnderTheSameCategory(pc_id);
    }
}
