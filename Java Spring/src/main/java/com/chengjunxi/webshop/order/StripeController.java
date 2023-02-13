package com.chengjunxi.webshop.order;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;


@RestController
@RequestMapping("/stripe")
public class StripeController {
    private final OrderRepository orderRepository;

    // @Autowired
    // private final MailNotification mailNotification;
    
	public StripeController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        // this.mailNotification = mailNotification;
	}

    @PostMapping("/webhook")
    void PostEvent(@RequestHeader("Stripe-Signature") String sigHeader, @RequestBody String payload) throws StripeException, InterruptedException{
        String endpointSecret = "whsec_95232a2e6138642cb57bad75bae22c5d24854fa3aeae1062ef62387ffe12538c";
        Event event;

        event = Webhook.constructEvent(
            payload, sigHeader, endpointSecret
        );

        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded": {
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                if (paymentIntent != null) {
                    Order order = orderRepository.findByStripe(paymentIntent.getClientSecret());
                    order.setPaid(true);
                    orderRepository.save(order);
                }
                // mailNotification.sendNotificaitoin(order.getEmail());
                break;
            }
            // ... handle other event types
            default:
                break;
        }
    }
}

