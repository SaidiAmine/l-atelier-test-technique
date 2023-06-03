package com.org.latelier.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

import java.util.List;

@Embeddable
public class PlayerData {

    long rank;
    long points;
    float weight;
    float height;
    int age;
    List<Integer> last;

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*Transient data: There may be fields in your entity class that hold temporary
    or transient data that should not be persisted. For example,
     if you have a field that holds a cache of computed data or a
     field used for temporary calculations, you can mark it as @Transient*/
    @Transient
    public List<Integer> getLast() {
        return last;
    }

    public void setLast(List<Integer> last) {
        this.last = last;
    }
}
