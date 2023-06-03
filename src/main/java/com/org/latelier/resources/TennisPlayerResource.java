package com.org.latelier.resources;

import com.org.latelier.models.PlayersResponseEntity;
import com.org.latelier.models.TennisStats;
import com.org.latelier.models.entities.TennisPlayerEntity;
import com.org.latelier.services.TennisPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class TennisPlayerResource {

    private final TennisPlayersService tennisPlayersService;

    @Value("${latelier.api.uri}")
    private String apiUri;

    @Autowired
    public TennisPlayerResource(TennisPlayersService tennisPlayersService) {
        this.tennisPlayersService = tennisPlayersService;
    }

    @GetMapping(value = "/players")
    public PlayersResponseEntity getAllTennisPlayersFromExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlayersResponseEntity> responseEntity = restTemplate.getForEntity(apiUri, PlayersResponseEntity.class);
        return responseEntity.getBody();
    }

    @GetMapping("/players/ordered")
    public List<TennisPlayerEntity> getAllTennisPlayersFromDbOrdered() {
        return tennisPlayersService.findAllTennisPlayersOrdered();
    }

    @GetMapping("/player/{id}")
    public TennisPlayerEntity getTennisPlayerById(@PathVariable Long id) {
        return tennisPlayersService.findTennisPlayerById(id);
    }

    @GetMapping("/stats")
    public TennisStats getTennisStats() {
        return tennisPlayersService.assembleTennisStats();
    }

}
