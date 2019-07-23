package com.asu.nestedrecyclerview.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("tracks")
    List<Track> trackList;

    @SerializedName("courses")
    List<Course> courseList;

//    public Result (List<Track> trackList){
//        this.trackList = trackList;
//    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
