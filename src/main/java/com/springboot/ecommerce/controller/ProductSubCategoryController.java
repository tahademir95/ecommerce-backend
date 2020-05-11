package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.ProductSubCategory;
import com.springboot.ecommerce.service.ProductSubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/product-subcategory")
@Tag(name = "Product SubCategory API")
public class ProductSubCategoryController {

    @Autowired
    ProductSubCategoryService subCategoryService;

    @Operation(summary = "Creates new product subcategory", description = "Creates new product subcategory",tags = { "Product SubCategory API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product SubCategory created",content = @Content(schema = @Schema(example = " ")))})
    @PostMapping(value = "/subcategory", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewSubCategory(@Parameter(description="Creates new product subcategory here",
                                                                required=true,
                                                                schema=@Schema(example = "{\n" +
                                                                        "    \"subCategoryName\": \"string\",\n" +
                                                                        "    \"productCategory\": {\n" +
                                                                        "        \"pc_id\": category_id_that_subcategory_is_related_to\n" +
                                                                        "    }\n" +
                                                                        "}")
                                                    )@RequestBody ProductSubCategory subCategory, UriComponentsBuilder ucBuilder) {
        subCategoryService.createSubCategory(subCategory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/subcategory/{id}").buildAndExpand(subCategory.getPsc_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the product subcategory which is specified by id", tags = { "Product SubCategory API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"psc_id\": product_subcategory_id,\n" +
                    "    \"subCategoryName\": \"string\",\n" +
                    "    \"subCategoryFeatures\": [\n" +
                    "        {\n" +
                    "            \"scf_id\": subcategory_feature_id,\n" +
                    "            \"subCategoryFeatureName\": null,\n" +
                    "            \"productFeatureDetails\": []\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}"))),
            @ApiResponse(responseCode = "500", description = "No elements with ID that you have specified",content = @Content(schema = @Schema(example = " ")))})
    @PutMapping(value="/subcategory/", headers="Accept=application/json")
    public ProductSubCategory updateSubCategory(@Parameter(description="Updates the product feature detail specified by ID", required=true,schema=@Schema(example = "{\n" +
                                                                "        \"psc_id\": product_subcategory_id,\n" +
                                                                "        \"subCategoryName\": \"string\",\n" +
                                                                "        \"productCategory\": {\n" +
                                                                "        \t\"pc_id\": product_category_id\n" +
                                                                "    \t},\n" +
                                                                "        \"subCategoryFeatures\": [\n" +
                                                                "            {\n" +
                                                                "                \"scf_id\": subcategory_feature_id_1\n" +
                                                                "            },\n" +
                                                                "            {\n" +
                                                                "                \"scf_id\": subcategory_feature_id_2\n" +
                                                                "            }\n" +
                                                                "        ]\n" +
                                                                "    }")
                                                          )@RequestBody ProductSubCategory subCategory)
    {
        return  subCategoryService.editSubCategory(subCategory);
    }

    @Operation(summary = "Deletes the product subcategory which is specified by id", description = "Deletes product subcategory", tags = { "Product SubCategory API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",content = @Content(schema = @Schema(example = " ")))})
    @DeleteMapping(value="/subcategory/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductSubCategory> deleteSubCategory(@Parameter(description = "ID of the product subcategory") @PathVariable("id") int id){
        ProductSubCategory subCategory = subCategoryService.findSubCategoryById(id);
        if (subCategory == null) {
            return new ResponseEntity<ProductSubCategory>(HttpStatus.NOT_FOUND);
        }
        subCategoryService.removeSubCategoryById(id);
        return new ResponseEntity<ProductSubCategory>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find product subcategory by ID", description = "Returns a single product subcategory", tags = { "Product SubCategory API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"psc_id\": product_subcategory_id,\n" +
                    "    \"subCategoryName\": \"string\",\n" +
                    "    \"subCategoryFeatures\": [\n" +
                    "        {\n" +
                    "            \"scf_id\": subcategory_feature_id,\n" +
                    "            \"subCategoryFeatureName\": \"string\",\n" +
                    "            \"productFeatureDetails\": [\n" +
                    "                {\n" +
                    "                    \"pfd_id\": product_feature_detail_id,\n" +
                    "                    \"productFeatureDetail\": \"string\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"scf_id\": subcategory_feature_id,\n" +
                    "            \"subCategoryFeatureName\": \"string\",\n" +
                    "            \"productFeatureDetails\": [\n" +
                    "                {\n" +
                    "                    \"pfd_id\": product_feature_detail_id,\n" +
                    "                    \"productFeatureDetail\": \"string\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}")))})
    @GetMapping(value = "/subcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductSubCategory> getSubCategoryById(@Parameter(description = "ID of the product subcategory") @PathVariable("id") int id) {

        ProductSubCategory subCategory = subCategoryService.findSubCategoryById(id);

        if (subCategory == null) {
            return new ResponseEntity<ProductSubCategory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductSubCategory>(subCategory, HttpStatus.OK);
    }

    @Operation(summary = "Find product subcategories as a list", description = "Lists all product subcategories", tags = { "Product SubCategory API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(example = "[\n" +
                            "{\n" +
                            "    \"psc_id\": product_subcategory_id_1,\n" +
                            "    \"subCategoryName\": \"string\",\n" +
                            "    \"subCategoryFeatures\": [\n" +
                            "        {\n" +
                            "            \"scf_id\": subcategory_feature_id,\n" +
                            "            \"subCategoryFeatureName\": \"string\",\n" +
                            "            \"productFeatureDetails\": [\n" +
                            "                {\n" +
                            "                    \"pfd_id\": product_feature_detail_id,\n" +
                            "                    \"productFeatureDetail\": \"string\"\n" +
                            "                }\n" +
                            "            ]\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"scf_id\": subcategory_feature_id,\n" +
                            "            \"subCategoryFeatureName\": \"string\",\n" +
                            "            \"productFeatureDetails\": [\n" +
                            "                {\n" +
                            "                    \"pfd_id\": product_feature_detail_id,\n" +
                            "                    \"productFeatureDetail\": \"string\"\n" +
                            "                }\n" +
                            "            ]\n" +
                            "        }\n" +
                            "    ]\n" +
                            "},\n" +
                            "{\n" +
                            "    \"psc_id\": product_subcategory_id_2,\n" +
                            "    \"subCategoryName\": \"string\",\n" +
                            "    \"subCategoryFeatures\": [\n" +
                            "        {\n" +
                            "            \"scf_id\": subcategory_feature_id,\n" +
                            "            \"subCategoryFeatureName\": \"string\",\n" +
                            "            \"productFeatureDetails\": [\n" +
                            "                {\n" +
                            "                    \"pfd_id\": product_feature_detail_id,\n" +
                            "                    \"productFeatureDetail\": \"string\"\n" +
                            "                }\n" +
                            "            ]\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"scf_id\": subcategory_feature_id,\n" +
                            "            \"subCategoryFeatureName\": \"string\",\n" +
                            "            \"productFeatureDetails\": [\n" +
                            "                {\n" +
                            "                    \"pfd_id\": product_feature_detail_id,\n" +
                            "                    \"productFeatureDetail\": \"string\"\n" +
                            "                }\n" +
                            "            ]\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}\n" +
                            "]"))) })
    @GetMapping(value="/subcategories", headers="Accept=application/json")
    public List<ProductSubCategory> getAllSubCategories() {
        return subCategoryService.getSubCategoryList();
    }
}
