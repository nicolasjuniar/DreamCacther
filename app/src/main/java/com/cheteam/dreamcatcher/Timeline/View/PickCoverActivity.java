package com.cheteam.dreamcatcher.Timeline.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterListCover;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class PickCoverActivity extends AppCompatActivity {
    RecyclerViewAdapterListCover adapter;
    @BindView(R.id.ListCover) RecyclerView recyclerView;
    List<String> ListCover;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_cover_layout);
        ButterKnife.bind(this);

        ListCover=new ArrayList<>();
        ListCover.add("1");
        ListCover.add("2");
        ListCover.add("3");
        ListCover.add("4");
        ListCover.add("5");

        adapter=new RecyclerViewAdapterListCover(ListCover,PickCoverActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PickCoverActivity.this));
    }
}
