package com.application.powerledgerapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.repository.BatteryRepository;

@Service
public class BatteryService {
	
	@Autowired
	private BatteryRepository batteryRepository;
	
	public List<Battery> create(List<Battery> listOfBatteries){
		return this.batteryRepository.saveAll(listOfBatteries);
	}
	
	public List<String> getNames(Long minPostcode, Long maxPostcode){
		List<Battery> batteriesInRange = this.batteryRepository.findByPostcodeBetween(minPostcode, maxPostcode);
		List<String> names = new ArrayList<>();
		
		for(Battery battery: batteriesInRange) {
			names.add(battery.getName());
		}
		return names;
	}
}