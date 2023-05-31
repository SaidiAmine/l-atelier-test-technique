package com.org.latelier.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TennisPlayer {

    long id;
    String firstName;
    String lastName;
    String shortName;
    String sex;
    Country country;
    PlayerData data;
}
