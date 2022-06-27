package com.application.powerledgerapp.service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.model.dto.StatisticsDto;
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
		
		return batteriesInRange.stream().map(Battery::getName).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}	
	
	public StatisticsDto getStatistics(Long minPostcode, Long maxPostcode) {
		List<String> names = getNames(minPostcode, maxPostcode);
		
		StatisticsDto statisticsDto = new StatisticsDto();
		statisticsDto.setWattCapacitySum(this.batteryRepository.sumOfCapacity(names));
		statisticsDto.setWatCapacityAverage(this.batteryRepository.avgOfCapacity(names));
		
		return statisticsDto;
	}
	
}
