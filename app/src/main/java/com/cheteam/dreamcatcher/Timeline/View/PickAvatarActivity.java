package com.cheteam.dreamcatcher.Timeline.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterListAvatar;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterListCover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class PickAvatarActivity extends AppCompatActivity {
    RecyclerViewAdapterListAvatar adapter;
    RecyclerView recyclerView;
    List<Integer> ListAvatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_avatar_layout);
        recyclerView=(RecyclerView) findViewById(R.id.ListAvatar);

        ListAvatar=new ArrayList<>();
        for(int i=1;i<=9;i++)
        {
            ListAvatar.add(i);
        }

        adapter=new RecyclerViewAdapterListAvatar(ListAvatar,PickAvatarActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
