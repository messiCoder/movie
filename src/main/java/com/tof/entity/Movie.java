package com.tof.entity;

import java.io.Serializable;

/**
 * Created by wanxiang on 2018/3/9.
 */
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id ;

    private String name;

    private String altName;

    private String imageUrl;

    private String score;

    private String director;

    private String actor;

    private String type;

    private String region;

    private String showTime;

    private String imdb;

    private String movieLong;

    private String otherName;

    private String movieStory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getMovieLong() {
        return movieLong;
    }

    public void setMovieLong(String movieLong) {
        this.movieLong = movieLong;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getMovieStory() {
        return movieStory;
    }

    public void setMovieStory(String movieStory) {
        this.movieStory = movieStory;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }
}
