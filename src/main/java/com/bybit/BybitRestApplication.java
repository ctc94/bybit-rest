package com.bybit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BybitRestApplication {
	private static final Logger log = LoggerFactory.getLogger(BybitRestApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BybitRestApplication.class, args);
	}
	
	
	@Bean
	@Profile("default") // Don't run from test(s)
	public ApplicationRunner runner() {
		return args -> {
			log.info("runner run.");
		};
	}

}
