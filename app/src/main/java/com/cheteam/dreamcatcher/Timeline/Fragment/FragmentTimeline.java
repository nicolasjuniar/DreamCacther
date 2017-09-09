package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListCategories;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListPost;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineAPI;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.Timeline.Model.ResponseTimeline;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 08/09/2017.
 */

public class FragmentTimeline extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ProgressBar progressBar;

    ArrayList<ModelTimeline> list;
    RecycleViewAdapterListPost adapter;

    ArrayList<String> ListCategories;
    RecycleViewAdapterListCategories adapter2;
    TextView txtEdit;

    TimelineAPI service;
    Call<ResponseTimeline> CallTimeline;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline_layout,
                container, false);

        progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
        swipeRefreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        recyclerView  = (RecyclerView) view.findViewById(R.id.ListPost);
        recyclerView2=(RecyclerView) view.findViewById(R.id.ListCategories);
        txtEdit=(TextView) view.findViewById(R.id.txtEdit);

        SetListPost();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SetListPost();
            }
        });

        SetListCategories();
        setFont();

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DialogFragmentSelectCategory().show(getFragmentManager(),"Select Category");
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
        return view;
    }

    public void SetListCategories()
    {
        ListCategories=new ArrayList<>();
        ListCategories.add("Finances");
        ListCategories.add("Facilities");
        ListCategories.add("Opportunities");
        ListCategories.add("Skills");
        ListCategories.add("Courses");
        adapter2=new RecycleViewAdapterListCategories(ListCategories,getContext());
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        txtEdit.setTypeface(Roboto_Regular);
    }

    public void SetListPost()
    {
        service= ServiceGenerator.createService(TimelineAPI.class);
        CallTimeline=service.GetTimeline();
        CallTimeline.enqueue(new Callback<ResponseTimeline>() {
            @Override
            public void onResponse(Response<ResponseTimeline> response) {
                list=response.body().posts;
                adapter=new RecycleViewAdapterListPost(list,getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
