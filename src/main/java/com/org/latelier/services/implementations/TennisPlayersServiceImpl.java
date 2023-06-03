package com.org.latelier.services.implementations;

import com.org.latelier.application.TennisPlayerMapper;
import com.org.latelier.exceptions.EntityNotFoundException;
import com.org.latelier.exceptions.ExternalApiCallException;
import com.org.latelier.models.*;
import com.org.latelier.models.entities.TennisPlayerEntity;
import com.org.latelier.repository.TennisPlayerRepository;
import com.org.latelier.services.TennisPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TennisPlayersServiceImpl implements TennisPlayersService {

    private final TennisPlayerRepository repository;
    private final TennisPlayerMapper tennisPlayerMapper;

    @Value("${latelier.api.uri}")
    private String apiUri;

    @Autowired
    public TennisPlayersServiceImpl(TennisPlayerRepository repository, TennisPlayerMapper tennisPlayerMapper) {
        this.tennisPlayerMapper = tennisPlayerMapper;
        this.repository = repository;
    }


    private PlayersResponseEntity fetchPlayersFromExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlayersResponseEntity> responseEntity = restTemplate.getForEntity(apiUri, PlayersResponseEntity.class);
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
            return responseEntity.getBody();
        } else throw new ExternalApiCallException("An error occurred while loading data from external api.");
    }

    @Override
    public void saveTennisPlayers() {
        PlayersResponseEntity playersResponseEntity = fetchPlayersFromExternalApi();
        if (playersResponseEntity != null && !playersResponseEntity.getPlayers().isEmpty()) {
            List<TennisPlayer> tennisPlayers = playersResponseEntity.getPlayers();
            List<TennisPlayerEntity> tennisPlayersEntities = tennisPlayerMapper.map(tennisPlayers);
            repository.saveAll(tennisPlayersEntities);
        }
    }

    @Override
    public List<TennisPlayerEntity> findAllTennisPlayersOrdered() {
        List<TennisPlayerEntity> playersList = repository.findAll();
        playersList.sort(Comparator.comparing(tennisPlayerEntity -> tennisPlayerEntity.getData().getRank()));
        return playersList;
    }

    @Override
    public TennisPlayerEntity findTennisPlayerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Couldn't find entity with id %d", id)));
    }

    public float calculateAverageIMC() {
        List<TennisPlayerEntity> tennisPlayerEntities = repository.findAll();
        float totalIMC = 0f;
        for (TennisPlayerEntity player : tennisPlayerEntities) {
            float imc = (player.getData().getWeight() / 1000) / ((player.getData().getHeight() / 100) * (player.getData().getHeight() / 100));
            totalIMC += imc;
        }
        return totalIMC / tennisPlayerEntities.size();
    }

    public float heightMedian() {
        float heightMedian;
        List<TennisPlayerEntity> entities = repository.findAll();
        List<Float> heightList = entities.stream().map(TennisPlayerEntity::getData)
                .map(PlayerData::getHeight)
                .sorted().collect(Collectors.toList());
        int listSize = entities.size();
        if (listSize % 2 == 1) {
            heightMedian = heightList.get(listSize / 2);
        } else {
            float firstValue = heightList.get(listSize / 2 - 1);
            float secondValue = heightList.get(listSize / 2);
            heightMedian = (firstValue + secondValue) / 2.0f;
        }
        return heightMedian;
    }

    public CountryRatio topCountryWithWinningRation() {
        List<TennisPlayerEntity> entities = repository.findAll();
        CountryRatio countryRatio = new CountryRatio();
        for (TennisPlayerEntity tennisPlayer : entities) {
            int countryScore = tennisPlayer.getData().getLast().stream().reduce(Integer::sum).orElse(0);
            if (countryScore > countryRatio.getCountryScore()) {
                countryRatio.setCountryCode(tennisPlayer.getCountry().getCode());
                countryRatio.setCountryScore(countryScore);
            }
        }
        return countryRatio;
    }

    @Override
    public TennisStats assembleTennisStats() {
        TennisStats tennisStats = new TennisStats();
        tennisStats.setAverageIMC(calculateAverageIMC());
        tennisStats.setHeightMedian(heightMedian());
        tennisStats.setCountryRatio(topCountryWithWinningRation());
        return tennisStats;
    }
}
