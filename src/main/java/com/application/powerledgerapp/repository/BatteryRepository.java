package com.application.powerledgerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.powerledgerapp.model.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

	List<Battery> findByPostcodeBetween(Long min, Long max);
}