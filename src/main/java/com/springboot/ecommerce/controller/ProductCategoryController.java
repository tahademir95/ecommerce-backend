package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.ProductCategory;
import com.springboot.ecommerce.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/product-category")
@Tag(name = "Product Category API")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService categoryService;

    @Operation(summary = "Creates new product category", description = "Creates new product category",tags = { "Product Category API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product Category created",content = @Content(schema = @Schema(example = " ")))})
    @PostMapping(value = "/category", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProduct(@Parameter(description="Adds a new category. Just specify name of the category here",
                                                            required=true,
                                                            schema=@Schema(example = "{\"categoryName\":\"string\"}")
                                                 )@RequestBody ProductCategory productCategory, UriComponentsBuilder ucBuilder) {
        categoryService.createProductCategory(productCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(productCategory.getPc_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @Operation(summary = "Updates the product category which is specified by id", description = "Updates the name of the category that is specified by ID", tags = { "Product Category API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Product Category", content = @Content(schema = @Schema(example = "{\n" +
                                                                                                                                            "    \"pc_id\": product_category_id,\n" +
                                                                                                                                            "    \"categoryName\": \"string\",\n" +
                                                                                                                                            "    \"subCategories\": []\n" +
                                                                                                                                            "}"))),
            @ApiResponse(responseCode = "500", description = "No elements with ID that you have specified",content = @Content(schema = @Schema(example = " ")))
    })
    @PutMapping(value="/category/", headers="Accept=application/json")
    public ProductCategory updateProduct(@Parameter(description="Updates the category name specified by ID",
                                                    required=true,
                                                    schema=@Schema(example = "{\n" +
                                                            "    \"pc_id\": product_category_id,\n" +
                                                            "    \"categoryName\": \"string\"\n" +
                                                            "}")
                                                    )@RequestBody ProductCategory productCategory) {
        return  categoryService.editProductCategory(productCategory);
    }

    @Operation(summary = "Deletes the product category which is specified by id", description = "Deletes product category", tags = { "Product Category API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deletes Specified Product Category", content = @Content(schema = @Schema(example = " ")))})
    @DeleteMapping(value="/category/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductCategory> deleteProduct(@Parameter(description = "ID of the product category") @PathVariable("id") int id){
        ProductCategory productCategory = categoryService.findCategoryById(id);
        if (productCategory == null) {
            return new ResponseEntity<ProductCategory>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeCategoryById(id);
        return new ResponseEntity<ProductCategory>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find product category by ID", description = "Returns a single product category", tags = { "Product Category API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finds the category by ID", content = @Content(schema = @Schema(implementation = ProductCategory.class))),
            @ApiResponse(responseCode = "500", description = "No category with this id", content = @Content(schema = @Schema(example = " ")))
    })
    @GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCategory> getTypeById(@Parameter(description = "ID of the product category") @PathVariable("id") int id) {

        ProductCategory productCategory = categoryService.findCategoryById(id);

        if (productCategory == null) {
            return new ResponseEntity<ProductCategory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }

    @Operation(summary = "Find product categories as a list", description = "Lists all product categories", tags = { "Product Category API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Finds all categories with their information",content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductCategory.class))))})
    @GetMapping(value="/categories", headers="Accept=application/json")
    public List<ProductCategory> getAllTypes() {
        return categoryService.getCategoryList();
    }
}
