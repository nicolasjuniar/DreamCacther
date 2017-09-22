package com.cheteam.dreamcatcher.Timeline.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterListAvatar;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterListCover;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class PickAvatarActivity extends AppCompatActivity {
    RecyclerViewAdapterListAvatar adapter;
    @BindView(R.id.ListAvatar) RecyclerView recyclerView;

    List<Integer> ListAvatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_avatar_layout);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListAvatar=new ArrayList<>();
        for(int i=1;i<=9;i++)
        {
            ListAvatar.add(i);
        }

        adapter=new RecyclerViewAdapterListAvatar(ListAvatar,PickAvatarActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home: onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        int position= recyclerView.getId();
        Intent intent = new Intent(this,recyclerView.getClass());
        Bundle bundle = new Bundle();
        bundle.putInt("COVERID", position);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
}
