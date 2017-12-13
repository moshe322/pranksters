package com.littlejohnny.commons.database.dataObjects;

import java.util.Map;
import java.util.Objects;

public class Game {
    private int id;
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String ico;
    private Map<String, String> images;
    private float rate;

    public Game(int id, String name, String shortDescription, String fullDescription, String ico, Map<String, String> images, float rate) {
        this(name, shortDescription, fullDescription, ico, images, rate);
        this.id = id;
    }

    public Game(String name, String shortDescription, String fullDescription, String ico, Map<String, String> images, float rate) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.ico = ico;
        this.images = images;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Float.compare(game.rate, rate) == 0 &&
                Objects.equals(name, game.name) &&
                Objects.equals(shortDescription, game.shortDescription) &&
                Objects.equals(fullDescription, game.fullDescription) &&
                Objects.equals(ico, game.ico) &&
                Objects.equals(images, game.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortDescription, fullDescription, ico, images, rate);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", ico='" + ico + '\'' +
                ", images=" + images +
                ", rate=" + rate +
                '}';
    }
}
