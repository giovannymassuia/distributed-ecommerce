package com.giovannymassuia.inventoryservice.services.grpc;

import com.giovannymassuia.grpc.inventory.*;
import com.giovannymassuia.inventoryservice.repositories.InventoryRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class InventoryServiceGrpcImpl extends InventoryServiceGrpc.InventoryServiceImplBase {
    
    private final InventoryRepository inventoryRepository;

    @Override
    public void findByProduct(FindInventoryByProductRequest request, StreamObserver<FindInventoryByProductResponse> responseObserver) {

        inventoryRepository.findByProductId(UUID.fromString(request.getProductId()))
                .ifPresentOrElse(inventory -> { 
                    responseObserver.onNext(FindInventoryByProductResponse.newBuilder()
                            .setInventory(inventory.toGrpc())
                            .build()); 
                    responseObserver.onCompleted(); 
                }, 
                () -> responseObserver.onError(Status.NOT_FOUND
                        .withDescription("Inventory not found for this product.")
                        .asRuntimeException()));
        
    }
}
