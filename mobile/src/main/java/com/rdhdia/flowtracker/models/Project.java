package com.rdhdia.flowtracker.models;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Project {

    private String id;
    private String name;

    public Project() {
    }

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
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
}
