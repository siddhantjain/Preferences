package com.tdw.preferences.models;

/**
 * Created by siddhaja on 1/10/2016.
 */
public class user {
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    private long id;
    private String name;
    private String location;

    public int getBaselineSteps() {
        return baselineSteps;
    }

    public void setBaselineSteps(int baselineSteps) {
        this.baselineSteps = baselineSteps;
    }

    private int baselineSteps;
}
