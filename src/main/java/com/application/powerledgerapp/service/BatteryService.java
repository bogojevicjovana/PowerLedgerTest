package com.application.powerledgerapp.service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.model.dto.BatteryDto;
import com.application.powerledgerapp.model.dto.StatisticsDto;
import com.application.powerledgerapp.repository.BatteryRepository;
import com.application.powerledgerapp.transform.BatteryTransform;

@Service
public class BatteryService {
	
	@Autowired
	private BatteryRepository batteryRepository;
	
	@Autowired
	private BatteryTransform batteryTransform;
	
	public List<BatteryDto> create(List<BatteryDto> listOfBatteries){
		List<Battery> batteryList = batteryRepository.saveAll(listOfBatteries.stream()
												      .map(batteryDto -> batteryTransform.transformToEntity(batteryDto))
												      .collect(Collectors.toList()));
		
		return batteryList.stream().map(battery -> batteryTransform.transformToDto(battery)).collect(Collectors.toList());
	}
	
	public List<String> getNames(Long minPostcode, Long maxPostcode){
		List<Battery> batteriesInRange = batteryRepository.findByPostcodeBetween(minPostcode, maxPostcode);
		
		return batteriesInRange.stream().map(Battery::getName).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}	
	
	public StatisticsDto getStatistics(Long minPostcode, Long maxPostcode) {
		List<String> names = getNames(minPostcode, maxPostcode);
		
		StatisticsDto statisticsDto = new StatisticsDto();
		statisticsDto.setWattCapacitySum(batteryRepository.sumOfCapacity(names));
		statisticsDto.setWatCapacityAverage(batteryRepository.avgOfCapacity(names));
		
		return statisticsDto;
	}
	
}
