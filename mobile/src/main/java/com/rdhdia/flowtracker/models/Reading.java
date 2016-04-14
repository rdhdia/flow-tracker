package com.rdhdia.flowtracker.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Reading extends RealmObject {

    @PrimaryKey
    private String id;
    private String time;
    private String flowValue;
    private int sessionOrder;

    public Reading() {
    }

    public Reading(String id, String time, String flowValue, int sessionOrder) {
        this.id = id;
        this.time = time;
        this.flowValue = flowValue;
        this.sessionOrder = sessionOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
