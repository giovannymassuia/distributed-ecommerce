package com.giovannymassuia.paymentservice.streams;

import com.giovannymassuia.kafka.order.Order;
import com.giovannymassuia.kafka.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@EnableBinding(Processor.class)
@Profile("!test")
@Slf4j
public class PaymentStream {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Payment consumeEmployeeDetails(Order order) {
        log.info("Let's process order details: {}", order);
        
        return Payment.newBuilder()
                .setOrderId(order.getOrderId())
                .setCreatedAt(LocalDateTime.now()
                        .atOffset(ZoneOffset.UTC).toInstant().toEpochMilli())
                .setPaid(true)
                .setChainCheck(order.getChainCheck().concat("{ps}"))
                .build();
    }
    
}
