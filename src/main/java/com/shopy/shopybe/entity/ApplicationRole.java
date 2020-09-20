package com.shopy.shopybe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "application_role")
@Getter
@Setter
@AllArgsConstructor
public class ApplicationRole {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "role")
    private Set<ApplicationUser> users;
}
