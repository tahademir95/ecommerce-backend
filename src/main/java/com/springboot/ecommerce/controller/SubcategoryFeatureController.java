package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.SubCategoryFeature;
import com.springboot.ecommerce.service.SubCategoryFeatureService;
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
@RequestMapping("/subcategory-feature")
@Tag(name = "SubCategory Feature API")
public class SubcategoryFeatureController {

    @Autowired
    SubCategoryFeatureService featureService;

    @Operation(summary = "Creates new subcategory feature", description = "Creates new subcategory feature",tags = { "SubCategory Feature API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product Category created", content = @Content(schema = @Schema(example = " ")))})
    @PostMapping(value = "/feature", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewSubCategoryFeature(@Parameter(description="Adds a new category. Just specify name of the category here",
                                                                       required=true,
                                                                       schema=@Schema(example = "{\n" +
                                                                               "    \"subCategoryFeatureName\": \"string\",\n" +
                                                                               "    \"subCategory\": {\n" +
                                                                               "    \t\"psc_id\":product_subcategory_id\n" +
                                                                               "    }\n" +
                                                                               "}")
                                                           )@RequestBody SubCategoryFeature feature, UriComponentsBuilder ucBuilder) {
        featureService.createSubCategoryFeature(feature);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/feature/{id}").buildAndExpand(feature.getScf_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the subcategory feature which is specified by id", tags = { "SubCategory Feature API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Subcategory Feature", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"scf_id\": subcategory_feature_id,\n" +
                    "    \"subCategoryFeatureName\": \"string\",\n" +
                    "    \"productFeatureDetails\": []\n" +
                    "}}"))),
            @ApiResponse(responseCode = "500", description = "No elements with ID that you have specified",content = @Content(schema = @Schema(example = " ")))
    })
    @PutMapping(value="/feature/", headers="Accept=application/json")
    public SubCategoryFeature updateSubCategoryFeature(@Parameter(description="Updates the category name specified by ID",
                                                                  required=true,
                                                                  schema=@Schema(example = "{\n" +
                                                                          "    \"scf_id\": subcategory_feature_id,\n" +
                                                                          "    \"subCategoryFeatureName\": \"string\",\n" +
                                                                          "    \"subCategory\": {\n" +
                                                                          "    \t\"psc_id\":product_subcategory_id\n" +
                                                                          "    }\n" +
                                                                          "}")
                                                      )@RequestBody SubCategoryFeature subCategoryFeature){
        return  featureService.editSubCategoryFeature(subCategoryFeature);
    }

    @Operation(summary = "Deletes the subcategory feature which is specified by id", description = "Deletes subcategory feature", tags = { "SubCategory Feature API" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = " ")))})
    @DeleteMapping(value="/feature/{id}", headers ="Accept=application/json")
    public ResponseEntity<SubCategoryFeature> deleteSubCategoryFeature(@Parameter(description = "ID of the subcategory feature")@PathVariable("id") int id){
        SubCategoryFeature subCategoryFeature = featureService.findSubCategoryFeatureById(id);
        if (subCategoryFeature == null) {
            return new ResponseEntity<SubCategoryFeature>(HttpStatus.NOT_FOUND);
        }
        featureService.removeSubCategoryFeatureById(id);
        return new ResponseEntity<SubCategoryFeature>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find subcategory feature by ID", description = "Returns a single subcategory feature", tags = { "SubCategory Feature API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"scf_id\": subcategory_feature_id,\n" +
                    "    \"subCategoryFeatureName\": \"string\",\n" +
                    "    \"productFeatureDetails\": []\n" +
                    "}")))})
    @GetMapping(value = "/feature/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryFeature> getSubCategoryFeatureId(@Parameter(description = "ID of the subcategory feature") @PathVariable("id") int id) {

        SubCategoryFeature feature = featureService.findSubCategoryFeatureById(id);

        if (feature == null) {
            return new ResponseEntity<SubCategoryFeature>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SubCategoryFeature>(feature, HttpStatus.OK);
    }

    @Operation(summary = "Find all subcategory features as a list", description = "Lists all subcategory features", tags = { "SubCategory Feature API" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(example = "[\n" +
                            "    {\n" +
                            "        \"scf_id\": subcategory_feature_id,\n" +
                            "        \"subCategoryFeatureName\": \"string\",\n" +
                            "        \"productFeatureDetails\": [\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detai_id,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            }\n" +
                            "        ]\n" +
                            "    }\n" +
                            "]")))})
    @GetMapping(value="/features", headers="Accept=application/json")
    public List<SubCategoryFeature> getAllSubCategoryFeatures() {
        return featureService.getSubCategoryFeatureList();
    }
}
