package com.mapofzones.statsextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StatsExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatsExtractorApplication.class, args);
	}

}
