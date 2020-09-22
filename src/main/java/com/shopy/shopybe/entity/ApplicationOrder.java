package com.shopy.shopybe.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "application_order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationOrder {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String destinationAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private ApplicationUser user;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = {@JoinColumn(name = "orderId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")})
    private Set<Product> products;
}
