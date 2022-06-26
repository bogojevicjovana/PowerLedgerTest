package com.application.powerledgerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.powerledgerapp.model.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

	List<Battery> findByPostcodeBetween(Long min, Long max);

	Battery findOneByName(String string);
	
	@Query(value = "SELECT sum(b.watt_capacity) from Battery b where b.name in :names", nativeQuery = true)
	int sumOfCapacity(List<String> names);
	
	@Query(value = "SELECT avg(b.watt_capacity) from Battery b where b.name in :names", nativeQuery = true)
	int avgOfCapacity(List<String> names);
}
