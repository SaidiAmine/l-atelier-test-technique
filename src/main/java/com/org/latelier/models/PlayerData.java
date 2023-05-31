package com.org.latelier.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PlayerData {

    long rank;
    long points;
    float weight;
    float height;
    int age;
    List<Integer> last;
}
