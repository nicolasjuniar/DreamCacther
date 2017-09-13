package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterMypost;
import com.cheteam.dreamcatcher.Timeline.API.TimelineAPI;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineController;
import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MPR on 9/8/2017.
 */

public class FragmentMypost extends Fragment implements TimelineController.onTimelineResponse{

    @BindView(R.id.ListMyPost) RecyclerView ListMyPost;
    RecyclerViewAdapterMypost adapter;
    TimelineController TC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mypost_layout,
                container, false);
        ButterKnife.bind(this,view);
        TC=new TimelineController(this);
        TC.getTimeline();
        return view;
    }

    @Override
    public void getTimelineResponse(boolean error, TimelineResponse response, Throwable t) {
        if(!error)
        {
            adapter=new RecyclerViewAdapterMypost(response.posts,getActivity());
            ListMyPost.setAdapter(adapter);
            ListMyPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}


