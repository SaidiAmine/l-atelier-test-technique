package com.org.latelier.com.org.latelier.models.entities;

import com.org.latelier.models.Country;
import com.org.latelier.models.PlayerData;
import com.org.latelier.models.entities.TennisPlayerEntity;
import jakarta.persistence.Embedded;

import java.util.Arrays;

public class PlayerBuilder {
    long id;
    String firstName;
    String lastName;
    String shortName;
    String sex;
    String picture;
    Country country;
    PlayerData data;

    public PlayerBuilder id(long id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PlayerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerBuilder shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public PlayerBuilder sex(String sex) {
        this.sex = sex;
        return this;
    }

    public PlayerBuilder picture(String picture) {
        this.picture = picture;
        return this;
    }
    public PlayerBuilder country(Country country) {
        this.country = country;
        return this;
    }

    public PlayerBuilder data(PlayerData data) {
        this.data = data;
        return this;
    }

    public static PlayerBuilder one() {
        return new PlayerBuilder()
                .firstName("Rafael")
                .lastName("Nadal")
                .sex("M")
                .shortName("R.NL")
                .data(new PlayerData(1, 2500, 80000, 183, 27, Arrays.asList(1, 0,1,0,1)));
//                    public PlayerData(long rank, long points, float weight, float height, int age, List<Integer> last) {
    }

    public TennisPlayerEntity build() {
        return new TennisPlayerEntity(id, firstName, lastName, shortName, sex, picture, country, data);
//TennisPlayerEntity(long id, String firstName, String lastName, String shortName, String sex, String picture, Country country, PlayerData data) {
    }
}
