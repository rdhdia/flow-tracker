package com.rdhdia.flowtracker.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Reading extends RealmObject {

    @PrimaryKey
    private int id;

    private String time;
    private String flowValue;
    private int sessionOrder;

    public Reading() {
    }

    public Reading(int id, String time, String flowValue, int sessionOrder) {
        this.id = id;
        this.time = time;
        this.flowValue = flowValue;
        this.sessionOrder = sessionOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(String flowValue) {
        this.flowValue = flowValue;
    }

    public int getSessionOrder() {
        return sessionOrder;
    }

    public void setSessionOrder(int sessionOrder) {
        this.sessionOrder = sessionOrder;
    }
}
