package com.application.powerledgerapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.service.BatteryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BatteryServiceTest {

	@Autowired
	private BatteryService batteryService;

	@Test
	@Order(1)
	public void contextLoads() throws Exception {
		assertThat(batteryService).isNotNull();
	}

	@Test
	@Order(2)
	public void saveBatteries_Success() {
		Battery b1 = new Battery(1L, "Victus", 12505L, 200L);
		Battery b2 = new Battery(2L, "Techno", 13005L, 300L);

		List<Battery> batteries = new ArrayList<>();
		batteries.add(b1);
		batteries.add(b2);

		List<Battery> saved = this.batteryService.create(batteries);
		assertFalse(saved.isEmpty());
	}
	
	@Test
	@Order(3)
	public void findInRange_Success() {
		List<String> names = this.batteryService.getNames(12000L, 13000L);
		assertTrue(names.isEmpty());
	}
	
	@Test
	@Order(4)
	public void findInRange_notSuccess() {
		List<String> names = this.batteryService.getNames(160000L, 170000L);
		assertTrue(names.isEmpty());
	}
	
}
