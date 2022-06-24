package com.application.powerledgerapp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.repository.BatteryRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@Transactional
public class BatteryRepositoryTest {
	
	@Autowired
	private BatteryRepository batteryRepository;
	
	@Test
	@Order(1)
	public void should_create_new_battery() {
		Battery battery = new Battery(1L, "Victus", 12500L, "3000 mAh");
		batteryRepository.save(battery);
		
		Battery batteryByName = batteryRepository.findOneByName("Victus");
		assertThat(batteryByName).isNotNull();
		
		System.out.println(batteryByName.toString());
	}
	
//	@Test
//	@Order(2)
//	public void givendIdThenSHouldREturnBatteryOfThatId() {
//		Battery batteryFirst = new Battery(1L, "Baterija1", 13000L, "4200 mAh");
//		Battery batterySecond = new Battery(1L, "Baterija2", 135000L, "4500 mAh");
//		
//		batteryRepository.save(batteryFirst);
//		batteryRepository.save(batterySecond);
//		
//		List<Battery> listOfBatteries = (List<Battery>) batteryRepository.findAll();
//		assertEquals("Baterija1", listOfBatteries.get(1).getName());
//		
//	}
	
	
}
