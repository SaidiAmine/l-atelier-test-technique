package com.org.latelier.resources;

import com.org.latelier.models.api.response.TennisPlayersResponse;
import com.org.latelier.services.TennisPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


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
    public ResponseEntity<TennisPlayersResponse> getAllTennisPlayersFromExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TennisPlayersResponse> responseEntity = restTemplate.getForEntity(apiUri, TennisPlayersResponse.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @GetMapping("/players/ordered")
    public ResponseEntity<Object> getAllTennisPlayersFromDbOrdered() {
        return ResponseEntity.ok(tennisPlayersService.findAllTennisPlayersOrdered());
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Object> getTennisPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(tennisPlayersService.findTennisPlayerById(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getTennisStats() {
        return ResponseEntity.ok(tennisPlayersService.assembleTennisStats());
    }

}
