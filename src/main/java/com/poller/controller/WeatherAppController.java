package com.poller.controller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.poller.beans.WeatherDetailResponse;
import com.poller.service.WeatherAppService;

@RestController
@RequestMapping("/weather/app")
public class WeatherAppController {

	@Autowired(required = true)
	private WeatherAppService service;

	public void setService(WeatherAppService service) {
		this.service = service;
	}

	@GetMapping("/details")
	public DeferredResult<ResponseEntity<WeatherDetailResponse>> retrieveWeatherDetails(HttpServletRequest request) {
		
		DeferredResult<ResponseEntity<WeatherDetailResponse>> response = new DeferredResult<>();
		// this will create new thread for every data for weather 
		ForkJoinPool.commonPool().submit(() -> {
			System.out.println("Processing in separate thread: "+Thread.currentThread().getName());
			WeatherDetailResponse we = new WeatherDetailResponse();
			// Call to service which will give you dynamic data
			we.setAppDetail(this.service.getCurrentTemp());
			ResponseEntity<WeatherDetailResponse> en = new ResponseEntity<WeatherDetailResponse>(we, HttpStatus.OK);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			response.setResult(en);
		});
		return response;
	}

}
