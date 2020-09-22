package com.shopy.shopybe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "application_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private ApplicationRole role;

    @ManyToMany
    @JoinTable(name = "favorite_product",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")})
    private Set<Product> favoriteProducts;

    @OneToMany(mappedBy = "user")
    private Set<ApplicationOrder> orders;
}
