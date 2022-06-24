package com.application.powerledgerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.powerledgerapp.model.Battery;
import com.application.powerledgerapp.service.BatteryService;

@RestController
public class BatteryController {
	
	@Autowired
	private BatteryService batteryService;
	
	@PostMapping(value = "/saveBatteries", produces = "application/json")
	public List<Battery> saveBatteries(@RequestBody List<Battery> listOfBatteries){
		return this.batteryService.create(listOfBatteries);
	}

}
