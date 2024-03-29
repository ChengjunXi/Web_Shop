package com.chengjunxi.webshop.product;
// import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
// import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {
    @Id
    // @GeneratedValue
    private int id;

    private String product_name;
    private double price;
    private String descri;
    private int stock;
    private String category;
}
