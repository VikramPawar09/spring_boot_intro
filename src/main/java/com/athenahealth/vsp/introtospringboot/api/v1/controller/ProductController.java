package com.athenahealth.vsp.introtospringboot.api.v1.controller;

import com.athenahealth.vsp.introtospringboot.core.service.ProductService;
import com.athenahealth.vsp.introtospringboot.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@Slf4j
public class ProductController {

    // 1. Basic

   /* @GetMapping(value="/{id}")
    //Product getProductById(@PathVariable Integer id) {
    ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        log.debug("Recieved id:"+ id);

        if(id <= 0)
        {
            log.error("Invalid Id:"+ id);
            Product product = new Product();
            product.setId(id);
           //  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be > 0");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }

        Product product = new Product();
        product.setId(id);
        product.setTitle("Product "+id);
        product.setPrice(BigDecimal.valueOf(1.23 * id));
        return ResponseEntity.ok(product);
    }*/


    // 2. Advance
    private final ProductService productService;

    public ProductController(ProductService productService ) {
        this.productService = productService;
    }
    /* @GetMapping(value="/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = new Product();

        log.debug("Recieved id:"+ id);

        try {
            product = this.productService.getProductById(id);
        } catch (IllegalArgumentException exception) {
            product = new Product();
            product.setId(id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
        return ResponseEntity.ok(product);
    }*/

    //3 with optional
    @GetMapping(value="/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = new Product();

        log.debug("Recieved id:"+ id);

        try {
            Optional<Product> productOptional = this.productService.getProductById(id);
            if(productOptional.isPresent()) {
                product = productOptional.get();
            } else {
                product = new Product();
                product.setId(id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(product);
            }
        } catch (IllegalArgumentException exception) {
            product = new Product();
            product.setId(id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
        return ResponseEntity.ok(product);
    }
}
