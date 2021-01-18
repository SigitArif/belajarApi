package com.example.belajarAPI.repository;

import com.example.belajarAPI.domain.CustomerVehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerVehicleRepository extends JpaRepository<CustomerVehicle,Integer>, JpaSpecificationExecutor<CustomerVehicle>{


}