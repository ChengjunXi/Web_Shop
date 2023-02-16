package com.chengjunxi.webshop.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findByStripe(String stripe_id);
}
