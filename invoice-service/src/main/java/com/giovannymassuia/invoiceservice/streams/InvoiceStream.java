package com.giovannymassuia.invoiceservice.streams;

import com.giovannymassuia.kafka.invoice.Invoice;
import com.giovannymassuia.kafka.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@Slf4j
public class InvoiceStream {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Invoice consumeEmployeeDetails(Payment payment) {
        log.info("Let's process payment details: {}", payment);

        return Invoice.newBuilder()
                .setOrderId(payment.getOrderId())
                .setInvoiceNumber(UUID.randomUUID().toString())
                .setCreatedAt(LocalDateTime.now()
                        .atOffset(ZoneOffset.UTC).toInstant().toEpochMilli())
                .setPaid(payment.getPaid())
                .setChainCheck(payment.getChainCheck().concat("{is}"))
                .build();
    }
    
}