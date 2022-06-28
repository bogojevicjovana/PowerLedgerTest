package com.application.powerledgerapp.transform;

import org.springframework.stereotype.Component;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.model.dto.BatteryDto;

@Component
public class BatteryTransform {
	
	public Battery transformToEntity(BatteryDto dto) {
		return Battery.builder().name(dto.getName()).postcode(dto.getPostcode()).wattCapacity(dto.getWattCapacity()).build();
	}

	public BatteryDto transformToDto(Battery entity) {
		return BatteryDto.builder().name(entity.getName()).postcode(entity.getPostcode()).wattCapacity(entity.getWattCapacity()).build();
	}
}
