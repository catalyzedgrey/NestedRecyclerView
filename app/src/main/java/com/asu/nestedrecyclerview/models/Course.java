package com.asu.nestedrecyclerview.models;

import com.google.gson.annotations.SerializedName;

public class Course {
    public final String type = "course";
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("desc")
    String description;
    @SerializedName("min_gpa")
    float minGpa;
    @SerializedName("image_url")
    String imgUrl;


    public Course(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getMinGpa() {
        return minGpa;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
