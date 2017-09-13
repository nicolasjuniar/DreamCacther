package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListCategories;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListPost;
import com.cheteam.dreamcatcher.Timeline.API.TimelineAPI;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineController;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 08/09/2017.
 */

public class FragmentTimeline extends Fragment implements TimelineController.onTimelineResponse{

    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ListPost) RecyclerView recyclerView;
    @BindView(R.id.ListCategories) RecyclerView recyclerView2;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.txtEdit) TextView txtEdit;

    RecycleViewAdapterListPost adapter;

    ArrayList<String> ListCategories;
    RecycleViewAdapterListCategories adapter2;

    TimelineController TC;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline_layout,
                container, false);

        ButterKnife.bind(this,view);
        TC=new TimelineController(this);
        TC.getTimeline();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TC.getTimeline();
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


    @Override
    public void getTimelineResponse(boolean error, TimelineResponse response, Throwable t) {
        if(!error)
        {
            adapter=new RecycleViewAdapterListPost(response.posts,getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
