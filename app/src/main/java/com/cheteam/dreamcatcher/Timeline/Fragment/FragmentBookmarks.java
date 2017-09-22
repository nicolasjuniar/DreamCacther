package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterBookmarks;
import com.cheteam.dreamcatcher.Timeline.API.TimelineAPI;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Controller.BookmarkController;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineController;
import com.cheteam.dreamcatcher.Timeline.Model.BookmarkResponse;
import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by MPR on 9/8/2017.
 */

public class FragmentBookmarks extends Fragment implements BookmarkController.onBookmarkResponse {

    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ListBookmarks) RecyclerView ListBookmarks;
    RecyclerViewAdapterBookmarks adapter;
    BookmarkController BC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bookmarks_layout, container, false);
        ButterKnife.bind(this,view);
        BC=new BookmarkController(this);
        BC.GetBookmark();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BC.GetBookmark();
            }
        });
        ListBookmarks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });


        return view;
    }

    @Override
    public void getBookmarkResponse (boolean error, BookmarkResponse response, Throwable throwable) {
        if(!error)
        {
            adapter=new RecyclerViewAdapterBookmarks(response.bookmark,getActivity());
            ListBookmarks.setAdapter(adapter);
            ListBookmarks.setLayoutManager(new LinearLayoutManager(getActivity()));
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
