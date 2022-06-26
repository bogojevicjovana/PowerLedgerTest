package com.application.powerledgerapp;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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
		Battery battery = new Battery(1L, "Victus", 12500L, 3000L);
		batteryRepository.save(battery);
		
		Battery batteryByName = batteryRepository.findOneByName("Victus");
		assertThat(batteryByName).isNotNull();
		
		System.out.println(batteryByName.toString());
	}	
}
