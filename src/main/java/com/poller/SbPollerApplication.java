package com.poller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.poller.controller.WeatherAppController;

@SpringBootApplication(scanBasePackages={
"com.poller", "com.poller.service"})
public class SbPollerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbPollerApplication.class, args);
	}

}
