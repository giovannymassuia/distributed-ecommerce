package com.giovannymassuia.catalogservice.jobs;

import com.giovannymassuia.catalogservice.producers.OrderProducer;
import com.giovannymassuia.kafka.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartJob {
    
    private final OrderProducer orderProducer;
    
    @Scheduled(fixedDelay = 2000)
    public void purchase() {
        log.info("Sending message " + LocalDateTime.now());
        
        Order order = Order.newBuilder()
                .setOrderId(UUID.randomUUID().toString())
                .setCreatedAt(LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().toEpochMilli())
                .setChainCheck("{cs}")
                .build();

        orderProducer.produce(order);
        
        log.info("Message sent=> " + order.toString());
    }
    
}
