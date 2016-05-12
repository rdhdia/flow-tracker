package com.rdhdia.flowtracker.models;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Bridge {

    private String id;
    private String name;
    private String location;
    private String latitude;
    private String longitude;

    public Bridge() {
    }

    public Bridge(String id, String name, String location, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Bridge(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.latitude = null;
        this.longitude = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
