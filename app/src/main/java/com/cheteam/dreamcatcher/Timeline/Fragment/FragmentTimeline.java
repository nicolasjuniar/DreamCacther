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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.NetworkUtils;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListCategories;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListPost;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineController;
import com.cheteam.dreamcatcher.Timeline.Interface.IChangeCategory;
import com.cheteam.dreamcatcher.Timeline.Interface.ISetCategory;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 08/09/2017.
 */

public class FragmentTimeline extends Fragment implements TimelineController.onTimelineResponse,ISetCategory {

    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ListPost) RecyclerView recyclerView;
    @BindView(R.id.ListCategories) RecyclerView recyclerView2;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.txtEdit) ImageView txtEdit;

    RecycleViewAdapterListPost adapter;

    ArrayList<String> ListInterest;
    ArrayList<ModelTimeline> ListPost;
    ArrayList<ModelTimeline> ListPost2;
    RecycleViewAdapterListCategories adapter2;

    TimelineController TC;

    NetworkUtils network;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline_layout,
                container, false);

        ButterKnife.bind(this,view);
        TC=new TimelineController(this);

        network=new NetworkUtils(getActivity());

        Bundle arguments = getArguments();
        ListInterest=arguments.getStringArrayList("listinterest");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimeline();
            }
        });

        SetListInterest();

        fetchTimeline();

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listinterest",ListInterest);
                DialogFragmentSelectCategory selectCategory = new DialogFragmentSelectCategory();
                selectCategory.setArguments(bundle);
                selectCategory.setListener(FragmentTimeline.this);
                selectCategory.show(getFragmentManager(),"Select Category");
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

    public void fetchTimeline()
    {
        if(network.isConnected())
        {
            TC.getTimeline();
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "phone is not connected to internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void SetListInterest()
    {
        adapter2=new RecycleViewAdapterListCategories(ListInterest,getContext());
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }


    @Override
    public void getTimelineResponse(boolean error, TimelineResponse response, Throwable t) {
        if(!error)
        {
            ListPost=response.posts;
            ListPost2=new ArrayList<>();
            for (ModelTimeline post: ListPost ) {
                if(ListInterest.contains(post.categories))
                {
                    ListPost2.add(post);
                }
            }
            adapter=new RecycleViewAdapterListPost(ListPost2,getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setCategory(ArrayList<String> ListInterest) {
        this.ListInterest=ListInterest;
        adapter2.setListCategories(this.ListInterest);
        adapter2.notifyDataSetChanged();
        fetchTimeline();

    }
}
