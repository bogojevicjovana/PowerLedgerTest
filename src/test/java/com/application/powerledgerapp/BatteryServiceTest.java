package com.application.powerledgerapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.application.powerledgerapp.model.dto.StatisticsDto;
import com.application.powerledgerapp.repository.BatteryRepository;
import com.application.powerledgerapp.service.BatteryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BatteryServiceTest {

	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private BatteryRepository batteryRepository;

	@Test
	@Order(1)
	public void contextLoads() throws Exception {
		assertThat(batteryService).isNotNull();
	}

	@Test
	@Order(2)
	public void createBatteries_Success() {
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
	public void findInRange_notSuccess_notSorted() {
		List<String> names = this.batteryService.getNames(12000L, 13000L);
		List<Battery> batteries = this.batteryRepository.findByPostcodeBetween(12000L, 13000L);
		
		List<String> result = new ArrayList<>();
		for(Battery b: batteries) {
			result.add(b.getName());
		}
		
		assertEquals(names, result);
	}
	
	@Test
	@Order(4)
	public void findInRange_noSuchPostcode() {
		List<String> names = this.batteryService.getNames(1600000L, 1700000L);
		List<Battery> batteries = this.batteryRepository.findByPostcodeBetween(1600000L, 1700000L);
		
		List<String> result = new ArrayList<>();
		for(Battery b: batteries) {
			result.add(b.getName());
		}
		
		assertEquals(names, result);
	}
	
	@Test
	@Order(5)
	public void findInRange_returnsBatteryName() {
		List<String> names = this.batteryService.getNames(12000L, 12300L);
		assertEquals("Venusta", names.get(0));
	}
	
	@Test
	@Order(6)
	public void getStatistics() {
		StatisticsDto statistics = this.batteryService.getStatistics(12000L, 12500L);
		List<Battery> result = this.batteryRepository.findByPostcodeBetween(12000L, 13000L);
		
		Long wattCapacitySum = result.get(0).getWattCapacity() + result.get(1).getWattCapacity();
		Long wattCapacityAvg = wattCapacitySum/2;
		
		StatisticsDto resultDto = new StatisticsDto();
		resultDto.setWatCapacityAverage(wattCapacityAvg.intValue());
		resultDto.setWattCapacitySum(wattCapacitySum.intValue());
		
		assertEquals(statistics, resultDto);
	}
}
