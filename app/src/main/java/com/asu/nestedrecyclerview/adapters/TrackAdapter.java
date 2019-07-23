package com.asu.nestedrecyclerview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asu.nestedrecyclerview.R;
import com.asu.nestedrecyclerview.activities.main.IAsyncTaskCallback;
import com.asu.nestedrecyclerview.models.Track;
import com.asu.nestedrecyclerview.services.UserServices;
import com.asu.nestedrecyclerview.utils.Utils;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder> {
    List<Track> tracksList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_track, viewGroup, false);
        return new TrackAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CourseAdapter courseAdapter = new CourseAdapter();
        RecyclerView recyclerView = myViewHolder.itemView.findViewById(R.id.subitem);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(courseAdapter);

//        adapter.refreshCourseAdapter();
        myViewHolder.bind(tracksList.get(i), courseAdapter);
    }

    @Override
    public int getItemCount() {
        return tracksList == null ? 0 : tracksList.size();
    }

    public void refreshTrackAdapter(List<Track> tracksList) {
        this.tracksList = tracksList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, IAsyncTaskCallback {
        TextView abbrvTV, nameTV;
        View subItem;
        CourseAdapter courseAdapter;
        Track track;
        ImageView imgView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.track_name_tv);
            abbrvTV = itemView.findViewById(R.id.track_abbrv_tv);
            subItem = itemView.findViewById(R.id.subitem);
            imgView = itemView.findViewById(R.id.expand_img_view);
            itemView.setOnClickListener(this);
        }

        public void bind(Track track, CourseAdapter courseAdapter) {
            boolean expanded = track.isExpanded();
            this.courseAdapter = courseAdapter;
            this.track = track;
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            //subItem.setVisibility(View.VISIBLE);
            switch (track.getName()) {
                case "Mobile - Android":
                    nameTV.setText(track.getName());
                    abbrvTV.setText("ANDR");
                    abbrvTV.setBackgroundColor(abbrvTV.getResources().getColor(R.color.cessColor));
                    break;

                case "Mobile - ios":
                    nameTV.setText(track.getName());
                    abbrvTV.setText("iOS");
                    abbrvTV.setBackgroundColor(abbrvTV.getResources().getColor(R.color.mtlColor));
                    break;

                case "Web - Frontend":
                    nameTV.setText(track.getName());
                    abbrvTV.setText("FRNT");
                    abbrvTV.setBackgroundColor(abbrvTV.getResources().getColor(R.color.mechColor));
                    break;

                case "Web - Backend":
                    nameTV.setText(track.getName());
                    abbrvTV.setText("BKND");
                    abbrvTV.setBackgroundColor(abbrvTV.getResources().getColor(R.color.bldgColor));
                    break;
            }

        }

        @Override
        public void onClick(View v) {
            new UserServices().new LoadCourseList(this).execute(Utils.coursesURL);
            boolean expanded = track.isExpanded();
            track.setExpanded(!expanded);
            imgView.animate().rotation(imgView.getRotation() + 180);
            notifyItemChanged(this.getAdapterPosition());
        }

        @Override
        public void onAsyncTaskComplete(List trackList) {
            courseAdapter.refreshCourseAdapter(trackList);
            notifyItemChanged(this.getOldPosition());
        }
    }
}
