package com.rdhdia.flowtracker.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class Person extends RealmObject {

    @PrimaryKey
    private String id;
    private String fullName;
    private String projectId;

    public Person() {
    }

    public Person(String id, String fullName, String projectId) {
        this.id = id;
        this.fullName = fullName;
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
