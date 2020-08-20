package com.giovannymassuia.catalogservice.producers;

import com.giovannymassuia.kafka.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    @Value("${kafka.topics.order}")
    private String orderTopic;
    
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void produce(Order order) {
        kafkaTemplate.send(orderTopic, order);
    }
}
