package com.poller.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class WeatherAppService {

	public String getCurrentTemp() {
		
		String[] temps = {"Cold", "Hot", "Humid", "Warm", "Rainy"};
		Random r = new Random();
		return temps[r.nextInt((4 - 0) + 1) + 0];
	}
	
}
