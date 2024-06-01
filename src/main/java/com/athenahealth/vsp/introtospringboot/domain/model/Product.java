package com.athenahealth.vsp.introtospringboot.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @Column
    private Integer id;
    @Column
    private String title;
    @Column
    private BigDecimal price;
}
