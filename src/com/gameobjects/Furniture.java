package com.gameobjects;

import java.net.URL;

public class Furniture {
    private String name;
    private String url;
    private Thing key;


    public Furniture(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Thing getKey() {
        return key;
    }

    public void setKey(Thing key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", key=" + key +
                '}';
    }
}

