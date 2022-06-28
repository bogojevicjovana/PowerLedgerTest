package com.application.powerledgerapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class BatteryDto {
	
	private String name;
	
	private Long postcode;
	
	private Long wattCapacity;

}
