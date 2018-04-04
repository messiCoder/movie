package com.tof.entity;

import java.io.Serializable;

/**
 * Created by wanxiang on 2018/3/9.
 */
public class DownAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id ;

    private Integer movieId;

    private String downName;

    private String downUrl;

    private String rp;

    private String size;

    private String seedUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getDownName() {
        return downName;
    }

    public void setDownName(String downName) {
        this.downName = downName;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public void setSeedUrl(String seedUrl) {
        this.seedUrl = seedUrl;
    }

}
