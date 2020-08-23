package com.giovannymassuia.inventoryservice.models;

import com.giovannymassuia.grpc.inventory.InventoryMessage;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Inventory {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @NotNull
    private UUID productId;
    
    @NotNull
    private Long quantityOnHand;
    
    public InventoryMessage toGrpc() {
        return InventoryMessage.newBuilder()
                .setId(this.getId().toString())
                .setProductId(this.getProductId().toString())
                .setQuantityOnHand(this.getQuantityOnHand())
                .build();
    }
}
