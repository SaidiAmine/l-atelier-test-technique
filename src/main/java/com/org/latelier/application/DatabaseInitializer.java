package com.org.latelier.application;

import com.org.latelier.services.TennisPlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final TennisPlayersService service;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);


    @Autowired
    public DatabaseInitializer(@Qualifier("tennisPlayersServiceImpl") TennisPlayersService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.saveTennisPlayers();
        logger.info("Database initialized");
    }
}
