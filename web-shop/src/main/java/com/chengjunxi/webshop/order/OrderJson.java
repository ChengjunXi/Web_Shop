package com.chengjunxi.webshop.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderJson {
    private String first_name;
    private String last_name;
    private String email;
    private String uaddress;
    private String city;
    private String postal_code;

    private ArrayList<OrderItem> order_items;
}
