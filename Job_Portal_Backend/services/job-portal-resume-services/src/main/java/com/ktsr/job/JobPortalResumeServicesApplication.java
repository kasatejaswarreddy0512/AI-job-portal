package com.ktsr.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobPortalResumeServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalResumeServicesApplication.class, args);
	}

}
