package com.org.latelier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.org.latelier")
@EnableJpaRepositories("com.org.latelier.repository")
@EntityScan("com.org.latelier.models.entities")
// Todo review annotations
public class LAtelierTestTechniqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(LAtelierTestTechniqueApplication.class, args);
    }

}
