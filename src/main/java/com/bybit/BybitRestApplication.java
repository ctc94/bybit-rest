package com.bybit;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import com.bybit.config.VaultConfig;

@SpringBootApplication
@EnableScheduling
public class BybitRestApplication {
	private static final Logger log = LoggerFactory.getLogger(BybitRestApplication.class);
	
	
	@Autowired
	Environment env;
	
	@Autowired
	VaultTemplate template;
	
	@Autowired
	VaultConfig vaultConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(BybitRestApplication.class, args);		
	}
	
	
	@PostConstruct
	private void postConstruct() {
		System.out.println("##########################");
		System.out.println("@PostConstruct");
		System.out.println("##########################");
		System.out.println("##########################" + env.getProperty("spring.redis.host"));
		System.out.println("##########################" + env.getProperty("spring.redis.port"));
		
	}
	
	
	@Bean
	@Profile("default") // Don't run from test(s)
	public ApplicationRunner runner() {
		return args -> {
			System.out.println("##########################");
			System.out.println("runner run.");
			System.out.println("##########################");
			
//			Properties p = System.getProperties();
//	        Enumeration keys = p.keys();
//	        while (keys.hasMoreElements()) {
//	            String key = (String) keys.nextElement();
//	            String value = (String) p.get(key);
//	            System.out.println(key + ": " + value);
//	        }
		};
	}

}
