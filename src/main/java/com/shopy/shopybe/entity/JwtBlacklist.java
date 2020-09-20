package com.shopy.shopybe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "jwt_blacklist")
@Getter
@Setter
@AllArgsConstructor
public class JwtBlacklist {

    @Id
    private String jwt;
    private LocalDateTime expiryDate;
}
