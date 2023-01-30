package com.chengjunxi.webshop.order;

import org.springframework.web.bind.annotation.*;

import com.chengjunxi.webshop.product.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
class OrderController {
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final ProductRepository productRepository;

	public OrderController(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
		this.productRepository = productRepository;
	}

	@PostMapping
	ClientSecret postOrder(@RequestBody OrderJson orderJson) throws StripeException{
		//Split order items and attach order id and save to database
		Order order = new Order();
		order.setFirst_name(orderJson.getFirst_name());
		order.setLast_name(orderJson.getLast_name());
		order.setEmail(orderJson.getEmail());
		order.setUaddress(orderJson.getUaddress());
		order.setCity(orderJson.getCity());
		order.setPostal_code(orderJson.getPostal_code());
		order.setPaid(false);

		double amount = 0;
		
		for (OrderItem item : orderJson.getOrder_items()) {
			if (item.getAmount() != 0) {
				item.setOrder_id(order.getId());
				item.setPrice(productRepository.findById(item.getProduct_id()).get().getPrice());
				orderItemRepository.save(item);
				amount += item.getAmount() * item.getPrice();
			}
		}

		//Payment Process
		Stripe.apiKey = "";
		PaymentIntentCreateParams params =
		PaymentIntentCreateParams
			.builder()
			.setAmount(Math.round(amount*100))
			.setCurrency("usd")
			.addPaymentMethodType("card")
			.build();
		PaymentIntent intent = PaymentIntent.create(params);
		order.setStripe(intent.getClientSecret());
		orderRepository.save(order);
		return new ClientSecret(intent.getClientSecret());
	}
}