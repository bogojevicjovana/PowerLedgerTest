package com.application.powerledgerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.application.powerledgerapp.controller.BatteryController;

@SpringBootTest
@Transactional
class BatteryControllerTest {
	
	@Autowired
	private BatteryController batteryController;
	
	@Test
	@Order(1)
	void testFindAll() {
		ResponseEntity<List<String>> foundInRange = batteryController.getBatteries(12000L, 12300L);
		assertEquals("Venusta", foundInRange.getBody().get(0));
	}
}
