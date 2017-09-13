package com.cheteam.dreamcatcher.CommentSection.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rahmat Al Hakam on 10/09/2017.
 */

public class MainComment extends AppCompatActivity{
    private RecyclerView list_comment;
    @BindView(R.id.my_toolbar_activity_comment) Toolbar myToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Comment(s)");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
    }

}
