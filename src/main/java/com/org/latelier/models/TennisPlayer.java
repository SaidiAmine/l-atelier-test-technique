package com.org.latelier.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TennisPlayer {

    long id;
    @JsonProperty("firstname")
    String firstName;
    @JsonProperty("lastname")
    String lastName;
    @JsonProperty("shortname")
    String shortName;
    String sex;
    String picture;
    Country country;
    PlayerData data;
}
