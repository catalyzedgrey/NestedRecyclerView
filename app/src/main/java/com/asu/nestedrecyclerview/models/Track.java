package com.asu.nestedrecyclerview.models;

import com.google.gson.annotations.SerializedName;

public class Track {
    public final String type = "track";
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    boolean isExpanded;

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }

    public boolean isExpanded() {
        return isExpanded;
    }
}
