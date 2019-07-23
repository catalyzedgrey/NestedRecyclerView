package com.asu.nestedrecyclerview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.nestedrecyclerview.R;
import com.asu.nestedrecyclerview.models.Course;

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, idTV, descTV;
        Course course;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name_tv);
            idTV = itemView.findViewById(R.id.id_tv);
            descTV = itemView.findViewById(R.id.description_tv);
        }

        private void bind(Course course) {
            nameTV.setText(course.getName());
            idTV.setText(course.getId()+"");
            descTV.setText(course.getDescription());
        }
    }
}
