package com.shopy.shopybe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "application_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApplicationUser {

    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

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
