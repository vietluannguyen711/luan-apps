package com.example.quizcartservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> items;

    public void addCartItem(Long itemId) {
        items.add(CartItem.builder().itemId(itemId).amount(1).build());
    }

    public void removeCartItem(Long cartItemId) {
        items.removeIf(ci -> ci.getId().equals(cartItemId));
    }
}
