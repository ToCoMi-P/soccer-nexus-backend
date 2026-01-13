package com.kickerz73.soccernexus_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SoccernexusBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoccernexusBackendApplication.class, args);
    }

    @Bean
    @Profile("prod")  // ← Jetzt WIRKT @Profile!
    CommandLineRunner onStartupComplete() {
        return args -> {
            System.out.println("Get Request wegen Restore vom Backup starten ");

            RestTemplate restTemplate = new RestTemplate();
            String pipedreamUrl = "https://eo7xt07pvvjmw7y.m.pipedream.net";
            
            try {
                String response = restTemplate.getForObject(pipedreamUrl, String.class);
                System.out.println("✓ Pipedream Response: " + response);
            } catch (Exception e) {
                System.err.println("✗ Pipedream Request fehlgeschlagen: " + e.getMessage());
            }
        };
    }

}
