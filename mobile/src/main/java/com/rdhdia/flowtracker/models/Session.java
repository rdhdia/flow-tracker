package com.rdhdia.flowtracker.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Session extends RealmObject {

    @PrimaryKey
    private String id;
    private String bridgeId;
    private String date;
    private String location;
    private String notes;

    public Session() {
    }

    public Session(String id, String bridgeId, String date, String location, String notes) {
        this.id = id;
        this.bridgeId = bridgeId;
        this.date = date;
        this.location = location;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(String bridgeId) {
        this.bridgeId = bridgeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
