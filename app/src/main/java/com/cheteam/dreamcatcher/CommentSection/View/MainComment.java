package com.cheteam.dreamcatcher.CommentSection.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cheteam.dreamcatcher.CommentSection.Adapter.RecycleViewAdapterComment;
import com.cheteam.dreamcatcher.CommentSection.Model.CommentModel;
import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.cheteam.dreamcatcher.CommentSection.Controller.CommentController;
/**
 * Created by Rahmat Al Hakam on 10/09/2017.
 */

public class MainComment extends AppCompatActivity implements CommentController.OnCommentResponse{
    private  CommentController controller;
    @BindView(R.id.list_comment) RecyclerView list_comment;
    @BindView(R.id.my_toolbar_activity_comment) Toolbar myToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        controller = new CommentController(this);
        controller.Comment(1,1);
        list_comment.setLayoutManager(new LinearLayoutManager(this));
        list_comment.setHasFixedSize(true);
    }

    @Override
    public void getCommentResponse(boolean error, CommentModel commentModel, Throwable t) {
        if(!error){
            list_comment.setAdapter(new RecycleViewAdapterComment(commentModel));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
