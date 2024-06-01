package com.athenahealth.vsp.introtospringboot.db.repository;

import com.athenahealth.vsp.introtospringboot.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
