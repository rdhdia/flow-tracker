package com.rdhdia.flowtracker.models;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Reading {

    private int id;
    private String time;
    private String flowValue;
    private int sessionOrder;
    private int sessionId;

    public Reading() {
    }

    public Reading(int id, String time, String flowValue, int sessionOrder, int sessionId) {
        this.id = id;
        this.time = time;
        this.flowValue = flowValue;
        this.sessionOrder = sessionOrder;
        this.sessionId = sessionId;
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

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
