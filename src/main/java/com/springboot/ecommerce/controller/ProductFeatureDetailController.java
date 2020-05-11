package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.ProductFeatureDetail;
import com.springboot.ecommerce.service.ProductFeatureDetailService;
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
@RequestMapping("/product-feature-detail")
@Tag(name = "Product Feature Detail API")
public class ProductFeatureDetailController {

    @Autowired
    ProductFeatureDetailService featureDetailService;

    @Operation(summary = "Creates new product feature detail", description = "Creates new product feature detail",tags = { "Product Feature Detail API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product Feature Detail Created",content = @Content(schema = @Schema(example = " ")))})
    @PostMapping(value = "/detail", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProductFeatureDetail(@Parameter(description="Creates new product feature detail here",
                                                                        required=true,
                                                                        schema=@Schema(example = "{\n" +
                                                                                "    \"productFeatureDetail\": \"string\",\n" +
                                                                                "    \"subCategoryFeature\":{\n" +
                                                                                "    \t\"scf_id\":subcategory_feature_id_that_pfd_is_related_to\n" +
                                                                                "    }\n" +
                                                                                "}")
                                                             )@RequestBody ProductFeatureDetail productFeatureDetail, UriComponentsBuilder ucBuilder) {
        featureDetailService.createProductFeatureDetail(productFeatureDetail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/detail/{id}").buildAndExpand(productFeatureDetail.getPfd_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the product feature detail which is specified by id", tags = { "Product Feature Detail API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = " {\n" +
            "    \"pfd_id\": product_feature_Detail_id,\n" +
            "    \"productFeatureDetail\": \"string\"\n" +
            "}"))),
            @ApiResponse(responseCode = "500", description = "No elements with ID that you have specified",content = @Content(schema = @Schema(example = " ")))})
    @PutMapping(value="/detail/", headers="Accept=application/json")
    public ProductFeatureDetail updateProductFeatureDetail(@Parameter(description="Updates the product feature detail specified by ID",
                                                                        required=true,schema=@Schema(example = "{\n" +
                                                                                "    \"pfd_id\":product_feature_detail_id,\n" +
                                                                                "    \"productFeatureDetail\": \"string\",\n" +
                                                                                "    \"subCategoryFeatureName\": \"string\",\n" +
                                                                                "    \"subCategoryFeature\":{\n" +
                                                                                "    \t\"scf_id\":subcategory_feature_id\n" +
                                                                                "    }\n" +
                                                                                "}")
                                                                     )@RequestBody ProductFeatureDetail productFeatureDetail)
    {
        return  featureDetailService.editProductFeatureDetail(productFeatureDetail);
    }

    @Operation(summary = "Deletes the product feature detail which is specified by id", description = "Deletes product feature detail", tags = { "Product Feature Detail API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = " ")))})
    @DeleteMapping(value="/detail/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductFeatureDetail> deleteProductFeatureDetail(@Parameter(description = "ID of the product feature detail") @PathVariable("id") int id){
        ProductFeatureDetail featureDetail = featureDetailService.findProductFeatureDetailById(id);
        if (featureDetail == null) {
            return new ResponseEntity<ProductFeatureDetail>(HttpStatus.NOT_FOUND);
        }
        featureDetailService.removeProductFeatureDetailById(id);
        return new ResponseEntity<ProductFeatureDetail>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find product feature detail by ID", description = "Returns a single product feature detail", tags = { "Product Feature Detail API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"pfd_id\": product_feature_detail_id,\n" +
                    "    \"productFeatureDetail\": \"string\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "500", description = "No category with this id", content = @Content(schema = @Schema(example = " ")))})
    @GetMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductFeatureDetail> getProductFeatureDetailById(@Parameter(description = "ID of the product feature detail") @PathVariable("id") int id) {

        ProductFeatureDetail featureDetail = featureDetailService.findProductFeatureDetailById(id);

        if (featureDetail == null) {
            return new ResponseEntity<ProductFeatureDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductFeatureDetail>(featureDetail, HttpStatus.OK);
    }

    @Operation(summary = "Find product feature details as a list", description = "Lists all product feature details", tags = { "Product Feature Detail API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",content = @Content(schema = @Schema(example = "[\n" +
                            "    {\n" +
                            "        \"pfd_id\": product_feature_detail_id_1,\n" +
                            "        \"productFeatureDetail\": \"string\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"pfd_id\": product_feature_detail_id_2,\n" +
                            "        \"productFeatureDetail\": \"string\"\n" +
                            "    }\n" +
                            "]"))) })
    @GetMapping(value="/details", headers="Accept=application/json")
    public List<ProductFeatureDetail> getAllProductFeatureDetails() {
        return featureDetailService.getProductFeatureDetailList();
    }
}
