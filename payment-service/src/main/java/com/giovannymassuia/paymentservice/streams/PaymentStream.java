package com.giovannymassuia.paymentservice.streams;

import com.giovannymassuia.kafka.order.Order;
import com.giovannymassuia.kafka.payment.Payment;
import com.giovannymassuia.paymentservice.services.grpc.InventoryGrpcClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@EnableBinding(Processor.class)
@Profile("!test")
@Slf4j
@RequiredArgsConstructor
public class PaymentStream {
    
    private final InventoryGrpcClientService inventoryGrpcClientService;

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Payment consumeEmployeeDetails(Order order) {
        log.info("Processing order message: {}", order);

        LocalDateTime now = LocalDateTime.now();
        
        log.info("productInventory: " + inventoryGrpcClientService
                .findByProduct(UUID.fromString("c33ecdb7-3968-4da1-94f4-67ed3f69cf71")));
        log.info("productInventory: " + inventoryGrpcClientService
                .findByProduct(UUID.fromString("c33ecdb7-3968-4da1-94f4-67ed3f69cf71")));
        log.info("productInventory: " + inventoryGrpcClientService
                .findByProduct(UUID.fromString("c33ecdb7-3968-4da1-94f4-67ed3f69cf71")));
//        log.info("productInventory: " + inventoryGrpcClientService
//                .findByProduct(UUID.fromString("c33ecdb7-3968-4da1-94f4-67ed3f69cf72")));
        
        return Payment.newBuilder()
                .setOrderId(order.getOrderId())
                .setPaymentId(order.getOrderId())
                .setCreatedAtFormatted(now.toString())
                .setCreatedAt(now.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli())
                .build();
    }
    
}
