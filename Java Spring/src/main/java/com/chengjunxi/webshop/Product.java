package com.chengjunxi.webshop;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    // @GeneratedValue
    private int id;

    private String product_name;
    private double price;
    private String descri;
    private int stock;
}
