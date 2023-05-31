package com.org.latelier.resources;

import com.org.latelier.models.PlayersResponseEntity;
import com.org.latelier.models.TennisPlayer;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController("")
public class TennisPlayerResource {

    @GetMapping(value = "/players")
    public PlayersResponseEntity getAllTennisPlayers() {
        String uri = "https://data.latelier.co/training/tennis_stats/headtohead.json";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

//        return restTemplate.exchange(uri, HttpMethod.GET, request, TennisPlayer[].class);
//        return restTemplate.getForObject(uri, TennisPlayer[].class);
        ResponseEntity<PlayersResponseEntity> responseEntity = restTemplate.getForEntity(uri, PlayersResponseEntity.class);
        return responseEntity.getBody();
    }

}
