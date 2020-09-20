package com.shopy.shopybe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="permission")
@Getter
@Setter
@AllArgsConstructor
public class Permission {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String value;
}
