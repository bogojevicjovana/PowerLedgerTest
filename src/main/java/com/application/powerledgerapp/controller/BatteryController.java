package com.application.powerledgerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.powerledgerapp.model.dto.BatteryDto;
import com.application.powerledgerapp.model.dto.StatisticsDto;
import com.application.powerledgerapp.service.BatteryService;

@RestController
@RequestMapping(value = "batteries")
public class BatteryController {
	
	@Autowired
	private BatteryService batteryService;
	
	@PostMapping(value = "/save", produces = "application/json")
	public List<BatteryDto> saveBatteries(@RequestBody List<BatteryDto> listOfBatteries){
		return this.batteryService.create(listOfBatteries);
	}
	
	@GetMapping(value = "/findAll/{min}/{max}", produces = "application/json")
	public ResponseEntity<List<String>> getBatteries(@PathVariable Long min, @PathVariable Long max){
		List<String> batteries = batteryService.getNames(min, max);
		return new ResponseEntity<>(batteries, HttpStatus.OK);
	}
	
	@GetMapping(value = "getStatistics/{min}/{max}", produces = "application/json")
	public ResponseEntity<StatisticsDto> getStatistics(@PathVariable Long min, @PathVariable Long max){
		StatisticsDto statistics = batteryService.getStatistics(min, max);
		return new ResponseEntity<>(statistics, HttpStatus.OK);
	}
		
}
