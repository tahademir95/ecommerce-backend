package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.service.ProductService;
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
@RequestMapping("/product-api")
@Tag(name = "Product API")
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Creates new product", description = "Creates new product",tags = { "Product" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product created",content = @Content(schema = @Schema(example = " ")))})
    @PostMapping(value = "/create-product", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProduct(@Parameter(description="Adds a new product. Specify some features of product shown below",
                                                            required=true,
                                                            schema=@Schema(example = "{\n" +
                                                                    "    \"productName\": \"string\",\n" +
                                                                    "    \"brandOfProduct\": \"string\",\n" +
                                                                    "    \"productFee\": int,\n" +
                                                                    "    \"productCategory\": {\n" +
                                                                    "        \"pc_id\": specify_the_category_that_the_product_will_be_under_of\n" +
                                                                    "    },\n" +
                                                                    "    \"subCategory\": {\n" +
                                                                    "        \"psc_id\":  specify_the_subcategory_that_the_product_will_be_under_of\n" +
                                                                    "    },\n" +
                                                                    "    \"featureDetails\": [\n" +
                                                                    "        {\n" +
                                                                    "            \"pfd_id\":specify_the_features_that_product_has\n" +
                                                                    "        },\n" +
                                                                    "        {\n" +
                                                                    "        \t\"pfd_id\":specify_the_features_that_product_has\n" +
                                                                    "        },\n" +
                                                                    "    ]\n" +
                                                                    "}")
                                                 )@RequestBody Product  product, UriComponentsBuilder ucBuilder){
        productService.createProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getP_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the product which is specified by id", tags = { "Product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Product Category", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"p_id\": product_id_that_will_be_updated,\n" +
                    "    \"productName\": \"string\",\n" +
                    "    \"brandOfProduct\": \"string\",\n" +
                    "    \"productFee\": int,\n" +
                    "    \"productCategory\": {\n" +
                    "        \"pc_id\": new_category_that_the_product_will_be_under_of,\n" +
                    "        \"categoryName\": null\n" +
                    "    },\n" +
                    "    \"subCategory\": {\n" +
                    "        \"psc_id\": new_specify_the_subcategory_that_the_product_will_be_under_of,\n" +
                    "        \"subCategoryName\": null\n" +
                    "    },\n" +
                    "    \"featureDetails\": [\n" +
                    "        {\n" +
                    "            \"pfd_id\": new_features_of_the_product,\n" +
                    "            \"productFeatureDetail\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"pfd_id\": new_features_of_the_product,\n" +
                    "            \"productFeatureDetail\": null\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}"))),
            @ApiResponse(responseCode = "500", description = "No elements with ID that you have specified",content = @Content(schema = @Schema(example = " ")))
    })
    @PutMapping(value="/update-product", headers="Accept=application/json")
    public Product updateProduct(@Parameter(description="Updates the product specified by ID",
                                            required=true,
                                            schema=@Schema(example = "{\n" +
                                                    "    \"p_id\":product_that_will_be_updated,\n" +
                                                    "    \"productName\": \"string\",\n" +
                                                    "    \"brandOfProduct\": \"string\",\n" +
                                                    "    \"productFee\": int,\n" +
                                                    "    \"productCategory\": {\n" +
                                                    "        \"pc_id\": specify_new_category_that_the_product_will_be_under_of\n" +
                                                    "    },\n" +
                                                    "    \"subCategory\": {\n" +
                                                    "        \"psc_id\": specify_new_subcategory_that_the_product_will_be_under_of\n" +
                                                    "    },\n" +
                                                    "    \"featureDetails\": [\n" +
                                                    "        {\n" +
                                                    "            \"pfd_id\": new_features_of_the_product\n" +
                                                    "        },\n" +
                                                    "        {\n" +
                                                    "            \"pfd_id\": new_features_of_the_product\n" +
                                                    "        }\n" +
                                                    "    ]\n" +
                                                    "}")
                                )@RequestBody Product product)
    {
        return  productService.editProduct(product);
    }

    @Operation(summary = "Deletes the product which is specified by id", description = "Deletes product", tags = { "Product" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deletes Specified Product", content = @Content(schema = @Schema(example = " "))) })
    @DeleteMapping(value="/delete-product/{id}", headers ="Accept=application/json")
    public ResponseEntity<Product> deleteProduct(@Parameter(description = "ID of the product") @PathVariable("id") int id){
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productService.removeProductById(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find product by ID", description = "Returns a single product", tags = { "Product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"p_id\": product_id,\n" +
                    "    \"productName\": \"string\",\n" +
                    "    \"brandOfProduct\": \"string\",\n" +
                    "    \"productFee\": int,\n" +
                    "    \"productCategory\": {\n" +
                    "        \"pc_id\": category_id_where_product_is_under_of,\n" +
                    "        \"categoryName\": \"string\"\n" +
                    "    },\n" +
                    "    \"subCategory\": {\n" +
                    "        \"psc_id\": subcategory_that_the_product_will_be_under_of,\n" +
                    "        \"subCategoryName\": \"string\"\n" +
                    "    },\n" +
                    "    \"featureDetails\": [\n" +
                    "        {\n" +
                    "            \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                    "            \"productFeatureDetail\": \"string\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                    "            \"productFeatureDetail\": \"string\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}"))),
            @ApiResponse(responseCode = "500", description = "No product with this id", content = @Content(schema = @Schema(example = " ")))
    })
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@Parameter(description = "ID of the product") @PathVariable("id") int id) {

        Product product = productService.findProductById(id);

        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @Operation(summary = "Find products as a list", description = "Lists all products", tags = { "Product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(example = "[\n" +
                            "    {\n" +
                            "        \"p_id\": product_id_1,\n" +
                            "        \"productName\": \"string\",\n" +
                            "        \"brandOfProduct\": \"string\",\n" +
                            "        \"productFee\": int,\n" +
                            "        \"productCategory\": {\n" +
                            "            \"pc_id\": category_id_where_product_is_under_of,\n" +
                            "            \"categoryName\": \"string\"\n" +
                            "        },\n" +
                            "        \"subCategory\": {\n" +
                            "            \"psc_id\": subcategory_that_the_product_will_be_under_of,\n" +
                            "            \"subCategoryName\": \"string\"\n" +
                            "        },\n" +
                            "        \"featureDetails\": [\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            },\n" +
                            "        ]\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"p_id\": product_id_2,\n" +
                            "        \"productName\": \"string\",\n" +
                            "        \"brandOfProduct\": \"string\",\n" +
                            "        \"productFee\": int,\n" +
                            "        \"productCategory\": {\n" +
                            "            \"pc_id\": category_id_where_product_is_under_of,\n" +
                            "            \"categoryName\": \"string\"\n" +
                            "        },\n" +
                            "        \"subCategory\": {\n" +
                            "            \"psc_id\": subcategory_that_the_product_will_be_under_of,\n" +
                            "            \"subCategoryName\": \"string\"\n" +
                            "        },\n" +
                            "        \"featureDetails\": [\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "                \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                            "                \"productFeatureDetail\": \"string\"\n" +
                            "            },\n" +
                            "        ]\n" +
                            "    }\n" +
                            "]")
                    )
            )
    })
    @GetMapping(value="/get-product-list", headers="Accept=application/json")
    public List<Product> getAllProducts() {
        return productService.getProductList();
    }

    @Operation(summary = "Find all products that are in the same subcategory", description = "Lists all products in the same subcategory ", tags = { "Product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products Under The Same Subcategory", content = @Content(schema = @Schema(example = "[\n" +
                    "    {\n" +
                    "      \"p_id\": product_id,\n" +
                    "      \"productName\": \"string\",\n" +
                    "      \"brandOfProduct\": \"string\",\n" +
                    "      \"productFee\": int,\n" +
                    "      \"productCategory\": {\n" +
                    "        \"pc_id\": category_id_where_product_is_under_of,\n" +
                    "        \"categoryName\": \"string\"\n" +
                    "      },\n" +
                    "      \"subCategory\": {\n" +
                    "        \"psc_id\": subcategory_id_where_product_is_under_of,\n" +
                    "        \"subCategoryName\": \"string\"\n" +
                    "      },\n" +
                    "      \"featureDetails\": [\n" +
                    "        {\n" +
                    "          \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                    "          \"productFeatureDetail\": \"string\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                    "          \"productFeatureDetail\": \"string\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "]"))
            )
    })
    @GetMapping(value="/get-product-list-in-the-same-subcategory/{psc_id}", headers="Accept=application/json")
    public List<Product> getAllProductsInTheSameSubCategory(@Parameter(description = "Id of the product subcategory",required=true) @PathVariable("psc_id") int psc_id,
                                                            @Parameter(description = "Specify the minimum cost of the product") @RequestParam(value = "minCost", required = false, defaultValue = "0") Integer minCost,
                                                            @Parameter(description = "Specify the maximum cost of the product") @RequestParam(value = "maxCost", required = false, defaultValue = "99999") Integer maxCost,
                                                            @Parameter(description = "Identify which brands of products you want to see") @RequestParam(value = "brandName", required = false) List<String> brandNameList) {

        if (((minCost != null && maxCost != null) &&  brandNameList != null )){
            return (List<Product>) productService.getProductListInTheSameSubCategory(psc_id, minCost, maxCost, brandNameList);
        }
        else {
            return (List<Product>) productService.getProductListInTheSameSubCategory(psc_id, minCost, maxCost);
        }
    }

    @Operation(summary = "Find all products that are in the same category", description = "Lists all products in the same category ", tags = { "Product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products Under The Same Category", content = @Content(schema = @Schema(example = "[\n" +
                    "    {\n" +
                    "      \"p_id\": product_id,\n" +
                    "      \"productName\": \"string\",\n" +
                    "      \"brandOfProduct\": \"string\",\n" +
                    "      \"productFee\": int,\n" +
                    "      \"productCategory\": {\n" +
                    "        \"pc_id\": category_id_where_product_is_under_of,\n" +
                    "        \"categoryName\": \"string\"\n" +
                    "      },\n" +
                    "      \"subCategory\": {\n" +
                    "        \"psc_id\": subcategory_id_where_product_is_under_of,\n" +
                    "        \"subCategoryName\": \"string\"\n" +
                    "      },\n" +
                    "      \"featureDetails\": [\n" +
                    "        {\n" +
                    "          \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                    "          \"productFeatureDetail\": \"string\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"pfd_id\": product_feature_detail_id_that_product_has,\n" +
                    "          \"productFeatureDetail\": \"string\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "]"))
            )
    })
    @GetMapping(value="/get-product-list-in-the-same-category/{pc_id}", headers="Accept=application/json")
    public List<Product> getAllProductsUnderTheSameCategory(@Parameter(description = "ID of the product category") @PathVariable("pc_id") int pc_id) {
        return (List<Product>) productService.getAllProductsUnderTheSameCategory(pc_id);
    }
}
