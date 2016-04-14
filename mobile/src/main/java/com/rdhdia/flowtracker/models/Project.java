package com.rdhdia.flowtracker.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Project extends RealmObject {

    @PrimaryKey
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
