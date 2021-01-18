package com.example.belajarAPI.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CUSTOMER_VEHICLE")
@Data
public class CustomerVehicle {
    @Id
    @Column(name="ID", nullable = false, unique = true)
    private String id;

    @Column(name="USER_ID", nullable = false)
    private String userId;

    @Column(name="USERNAME", nullable = false)
    private String username;

    @Column(name="REG_CODE", nullable = true)
    private String regCode;
    @Column(name="TOKEN", nullable = true)
    private String token;
}