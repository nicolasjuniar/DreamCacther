package com.cheteam.dreamcatcher.Timeline.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineAPI;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListPost;
import com.cheteam.dreamcatcher.Timeline.Model.ResponseTimeline;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 31/08/2017.
 */

public class TimelineActivity extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    ArrayList<ModelTimeline> list;
    RecycleViewAdapterListPost adapter;
    boolean check;

    TimelineAPI service;
    Call<ArrayList<ModelTimeline>> CallTimeline;

    MenuItem btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timeline_layout);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView  = (RecyclerView) findViewById(R.id.ListPost);

        Bundle bundle = getIntent().getExtras();
        check=bundle.getBoolean("login",false);
//
//        /////set Action Bar
//        final ActionBar actionBar = getSupportActionBar();
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View mCustomView = inflater.inflate(R.layout.actionbar_custom_1, null);
//        actionBar.setCustomView(mCustomView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//
//        Toolbar toolbar=(Toolbar)actionBar.getCustomView().getParent();
//        toolbar.setContentInsetsAbsolute(0, 0);
//        toolbar.getContentInsetEnd();
//        toolbar.setPadding(0, 0, 0, 0);
//
//        //////////////////

        setFont();
        SetListPost();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SetListPost();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        btnLogin= menu.findItem(R.id.action_login);

        if(check)
        {
            btnLogin.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.action_login)
        {
            Intent intent=new Intent(TimelineActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
    }

    public void SetListPost()
    {
        service= ServiceGenerator.createService(TimelineAPI.class);
        CallTimeline=service.GetTimeline();
        CallTimeline.enqueue(new Callback<ArrayList<ModelTimeline>>() {
            @Override
            public void onResponse(Response<ArrayList<ModelTimeline>> response) {
                list=response.body();
                adapter=new RecycleViewAdapterListPost(list,TimelineActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(TimelineActivity.this));
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
