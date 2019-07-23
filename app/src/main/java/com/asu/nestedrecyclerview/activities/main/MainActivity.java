package com.asu.nestedrecyclerview.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;

import com.asu.nestedrecyclerview.R;
import com.asu.nestedrecyclerview.adapters.TrackAdapter;
import com.asu.nestedrecyclerview.services.UserServices;
import com.asu.nestedrecyclerview.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IAsyncTaskCallback {
    TrackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        For RecyclerView
//        MovieAdapter adapter = new MovieAdapter();
//        RecyclerView recyclerView = findViewById(R.id.recview);
//        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter.refreshCourseAdapter(fillList());
//        recyclerView.refreshCourseAdapter(adapter);


        adapter = new TrackAdapter();
        RecyclerView recyclerView = findViewById(R.id.recview);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        UserServices outer = new UserServices();
        outer.new LoadTrackList(this).execute(Utils.trackURL);

    }

    @Override
    public void onClick(View v) {
        if (v.getTag().toString().equals("main_tv")) {

        }
    }


    @Override
    public void onAsyncTaskComplete(List trackList) {
        adapter.refreshTrackAdapter(trackList);
    }
}
