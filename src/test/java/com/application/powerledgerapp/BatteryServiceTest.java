package com.application.powerledgerapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.model.dto.BatteryDto;
import com.application.powerledgerapp.model.dto.StatisticsDto;
import com.application.powerledgerapp.repository.BatteryRepository;
import com.application.powerledgerapp.service.BatteryService;
import com.application.powerledgerapp.transform.BatteryTransform;


@SpringBootTest
@Transactional
class BatteryServiceTest {

	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private BatteryRepository batteryRepository;
	
	@Test
	@Order(1)
	void contextLoads() throws Exception {
		assertThat(batteryService).isNotNull();
	}

	@Test
	@Order(2)
	void createBatteries_Success() {
		BatteryDto b1 = new BatteryDto("Victus", 12505L, 200L);
		BatteryDto b2 = new BatteryDto("Techno", 13005L, 300L);

		List<BatteryDto> batteries = new ArrayList<>();
		batteries.add(b1);
		batteries.add(b2);
		List<BatteryDto> saved = this.batteryService.create(batteries);
		assertFalse(saved.isEmpty());
	}
		
	@Test
	@Order(3)
	void findInRange_noSuchPostcode() {
		List<String> names = batteryService.getNames(1600000L, 1700000L);
		List<Battery> batteries = batteryRepository.findByPostcodeBetween(1600000L, 1700000L);
		
		List<String> result = new ArrayList<>();
		for(Battery b: batteries) {
			result.add(b.getName());
		}
		
		assertEquals(names, result);
	}
	
	@Test
	@Order(4)
	void findInRange_returnsBatteryName() {
		List<String> names = batteryService.getNames(12000L, 12300L);
		assertEquals("Venusta", names.get(0));
	}
	
	@Test
	@Order(5)
	void getStatistics() {
		StatisticsDto statistics = batteryService.getStatistics(12000L, 12500L);
		List<Battery> result = batteryRepository.findByPostcodeBetween(12000L, 13000L);
		
		Long wattCapacitySum = result.get(0).getWattCapacity() + result.get(1).getWattCapacity();
		Long wattCapacityAvg = wattCapacitySum/2;
		
		StatisticsDto resultDto = new StatisticsDto();
		resultDto.setWatCapacityAverage(wattCapacityAvg.intValue());
		resultDto.setWattCapacitySum(wattCapacitySum.intValue());
		
		assertEquals(statistics, resultDto);
	}
}
