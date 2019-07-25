package com.asu.nestedrecyclerview.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.nestedrecyclerview.R;
import com.asu.nestedrecyclerview.activities.CourseDetailsActivity;
import com.asu.nestedrecyclerview.models.Course;
import com.google.gson.Gson;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    List<Course> coursesList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course, viewGroup, false);
        return new CourseAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(coursesList.get(i));
    }

    @Override
    public int getItemCount() {
        return coursesList == null ? 0 : coursesList.size();
    }

    public void refreshCourseAdapter(List<Course> coursesList) {
        this.coursesList = coursesList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTV, idTV, descTV;
        Course course;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name_tv);
            idTV = itemView.findViewById(R.id.id_tv);
            descTV = itemView.findViewById(R.id.description_tv);

            itemView.setOnClickListener(this);
        }

        private void bind(Course course) {
            this.course = course;
            nameTV.setText(course.getCourseName());
            idTV.setText(course.getCourseId()+"");
            descTV.setText(course.getCourseDescription());
        }

        @Override
        public void onClick(View v) {
            String courseJson = (new Gson()).toJson(this.course);
            Intent i = new Intent(v.getContext(), CourseDetailsActivity.class);
            i.putExtra("course", courseJson);
            v.getContext().startActivity(i);
        }
    }
}
