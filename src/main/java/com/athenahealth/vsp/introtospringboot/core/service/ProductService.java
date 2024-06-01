package com.athenahealth.vsp.introtospringboot.core.service;

import com.athenahealth.vsp.introtospringboot.db.repository.ProductRepository;
import com.athenahealth.vsp.introtospringboot.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    /* public Product getProductById(@PathVariable Integer id) {
        if(id <= 0)
        {
            log.error("Invalid Id:"+ id);
             throw new IllegalArgumentException("Id must be > 0");
        }

        Product product = new Product();
        product.setId(id);
        product.setTitle("Product "+id);
        product.setPrice(BigDecimal.valueOf(1.23 * id));
        return product;
    }*/

    // 2
    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;
    public ProductService(RestTemplateBuilder restTemplateBuilder, ProductRepository productRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.productRepository = productRepository;
    }

   /* public Product getProductById(@PathVariable Integer id) {
        if(id <= 0)
        {
            log.error("Invalid Id:"+ id);
            throw new IllegalArgumentException("Id must be > 0");
        }

        log.debug("Calling external with ID:"+ id);

        ResponseEntity<Product> productResponseEntity = this.restTemplate
                .getForEntity("https://fakestoreapi.com/products/{id}", Product.class, id );

        log.debug("External responded with Statuscode: "+productResponseEntity.getStatusCode().value());


        Product product = productResponseEntity.getBody();
        return product;
    }
    */

   // 3
   /* public Optional<Product> getProductById(@PathVariable Integer id) {
        if(id <= 0)
        {
            log.error("Invalid Id:"+ id);
            throw new IllegalArgumentException("Id must be > 0");
        }

        log.debug("Calling external with ID:"+ id);

        ResponseEntity<Product> productResponseEntity = this.restTemplate
                .getForEntity("https://fakestoreapi.com/products/{id}", Product.class, id );

        log.debug("External responded with Statuscode: "+productResponseEntity.getStatusCode().value());


        Product product = productResponseEntity.getBody();
        return Optional.ofNullable(product);
    }*/

    public Optional<Product> getProductById(@PathVariable Integer id) {
        if(id <= 0)
        {
            log.error("Invalid Id:"+ id);
            throw new IllegalArgumentException("Id must be > 0");
        }
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent()) {
            log.debug("Fond product with id " +id + " in database");
            return productOptional;
        }

        log.debug("Calling external with id:" + id);
        ResponseEntity<Product> productResponseEntity = this.restTemplate
                .getForEntity("https://fakestoreapi.com/products/{id}", Product.class, id );

        log.debug("External responded with Statuscode: "+productResponseEntity.getStatusCode().value());


        Product product = productResponseEntity.getBody();

        if(product !=null) {
            log.debug("Saving product with ID:" + id);
            this.productRepository.save(product);
        }

        return Optional.ofNullable(product);
    }
}
