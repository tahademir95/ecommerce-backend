package com.springboot.ecommerce.util;

import com.springboot.ecommerce.model.Product;
import com.springboot.ecommerce.service.ProductServiceImpl;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UtilsForProduct {

    public static List<EntityModel<Product>> getEntityModels(List<Product> productList) {
        return StreamSupport.stream(productList.spliterator(), false)
                .map(product -> new EntityModel<>(product, linkTo(methodOn(ProductServiceImpl.class).findProductById(product.getP_id()))
                        .slash("product")
                        .slash("product")
                        .slash(product.getP_id())
                        .withRel("products")))
                .collect(Collectors.toList());
    }
}
