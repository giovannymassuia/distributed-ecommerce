package com.giovannymassuia.paymentservice.services.grpc;

import com.giovannymassuia.grpc.inventory.FindInventoryByProductRequest;
import com.giovannymassuia.grpc.inventory.FindInventoryByProductResponse;
import com.giovannymassuia.grpc.inventory.InventoryMessage;
import com.giovannymassuia.grpc.inventory.InventoryServiceGrpc.InventoryServiceBlockingStub;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryGrpcClientService {
    
    @GrpcClient("inventory-service")
    private InventoryServiceBlockingStub blockingStub;

    public InventoryMessage findByProduct(UUID productId) {
        
        try {
            FindInventoryByProductResponse response = blockingStub
                    .findByProduct(FindInventoryByProductRequest.newBuilder()
                    .setProductId(productId.toString())
                    .build());

            return response.getInventory();
        } catch (StatusRuntimeException e) {
            if(Status.NOT_FOUND.equals(e.getStatus())) {
                return null;
            } else {
                throw new RuntimeException(e);
            }
        }
    }
    
}
