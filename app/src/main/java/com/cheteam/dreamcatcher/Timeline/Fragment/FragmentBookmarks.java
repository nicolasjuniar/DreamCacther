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

import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterBookmarks;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineAPI;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Model.ResponseTimeline;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by MPR on 9/8/2017.
 */

public class FragmentBookmarks extends Fragment {

    private final String TAG = FragmentBookmarks.class.getSimpleName();

    TimelineAPI service;
    Call<ResponseTimeline> CallBookmark;
    RecyclerView ListBookmarks;
    RecyclerViewAdapterBookmarks adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bookmarks_layout, container, false);
        ListBookmarks = (RecyclerView) view.findViewById(R.id.ListBookmarks);
        BookmarkPost();
        return view;
    }

    public void BookmarkPost(){
        service= ServiceGenerator.createService(TimelineAPI.class);
        CallBookmark=service.GetTimeline();
        CallBookmark.enqueue(new Callback<ResponseTimeline>() {
            @Override
            public void onResponse(Response<ResponseTimeline> response) {
                adapter=new RecyclerViewAdapterBookmarks(response.body().posts,getActivity());
                ListBookmarks.setAdapter(adapter);
                ListBookmarks.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView Bookmarks");
    }
}
