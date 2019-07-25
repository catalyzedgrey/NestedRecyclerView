package com.asu.nestedrecyclerview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.asu.nestedrecyclerview.R;
import com.asu.nestedrecyclerview.models.Course;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class CourseDetailsActivity extends AppCompatActivity {

    ImageView courseImg, instructorImg;
    TextView courseName, instructorName, courseDesc, instructorEmail;
    ImageButton enrollBtn;


    Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        retrieveCourseFromJson();
        initViews();
    }

    private void retrieveCourseFromJson(){
        String courseJson = getIntent().getStringExtra("course");
        course = (new Gson()).fromJson(courseJson, Course.class);
    }

    private void initViews(){
        courseImg =findViewById(R.id.course_img);
        courseName = findViewById(R.id.course_name);
        enrollBtn = findViewById(R.id.enroll_btn);
        //bind views
        Glide.with(this).load(course.getCourseImageUrl()).into(courseImg);
        courseName.setText(course.getCourseName());

        instructorImg = findViewById(R.id.instructor_img);
        instructorName = findViewById(R.id.instructor_name);
        instructorEmail = findViewById(R.id.instructor_email);
        //bind views
        Glide.with(this).load(course.getInstructor().getInstructorImageUrl()).into(instructorImg);
        instructorName.setText(course.getInstructor().getInstructorName());
        instructorEmail.setText(course.getInstructor().getEmail());

        courseDesc = findViewById(R.id.course_desc);
        courseDesc.setText(course.getCourseDescription());

    }
}
