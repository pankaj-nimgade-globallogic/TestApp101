package com.example.pankajnimgade.pankajtestapp;

/**
 * Created by Pankaj.Nimgade on 17-10-2016.
 */

public class MyItem {

    private String name;
    private String metadata;
    private Class activityClass;

    public MyItem(String name, String metadata) {
        this.name = name;
        this.metadata = metadata;
    }

    public MyItem(String name, String metadata, Class activityClass) {
        this(name, metadata);
        this.activityClass = activityClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}
