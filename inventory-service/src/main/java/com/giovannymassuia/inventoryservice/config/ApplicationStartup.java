package com.giovannymassuia.inventoryservice.config;

import com.giovannymassuia.inventoryservice.models.Inventory;
import com.giovannymassuia.inventoryservice.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    
    private final InventoryRepository inventoryRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        seedData();
    }

    private void seedData() {
        inventoryRepository.saveAll(List.of(
                Inventory.builder()
                        .productId(UUID.fromString("ac99a0b8-25a2-40f2-b9a5-d3ed698d24b7"))
                        .quantityOnHand(10l)
                        .build(),
                Inventory.builder()
                        .productId(UUID.fromString("d75648cd-051a-4d9e-aa06-7faafd403b72"))
                        .quantityOnHand(15l)
                        .build(),
                Inventory.builder()
                        .productId(UUID.fromString("c33ecdb7-3968-4da1-94f4-67ed3f69cf71"))
                        .quantityOnHand(20l)
                        .build()
        ));
    }

}