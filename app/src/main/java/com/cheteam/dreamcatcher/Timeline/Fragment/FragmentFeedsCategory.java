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
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.NetworkUtils;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterFeedsCategories;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListPost;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineController;
import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Nicolas Juniar on 09/09/2017.
 */

public class FragmentFeedsCategory extends Fragment implements TimelineController.onTimelineCategoryResponse{

    ArrayList<String> ListCategories;
    RecycleViewAdapterFeedsCategories adapterCategories;
    RecycleViewAdapterListPost adapterPosts;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ListCategories) RecyclerView recyclerView;
    @BindView(R.id.ListPost) RecyclerView recyclerView2;
    @BindView(R.id.bgCategory) ImageView bgCategory;
    @BindView(R.id.expand) ImageView expand;
    @BindView(R.id.txtCategoryName) TextView txtCategoryName;
    TimelineController TC;
    NetworkUtils network;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_categories_layout,
                container, false);
        ButterKnife.bind(this,view);
        setCategories();
        recyclerView2.setVisibility(View.GONE);
        bgCategory.setVisibility(View.GONE);
        txtCategoryName.setVisibility(View.GONE);
        setFont();
        TC=new TimelineController(this);
        network=new NetworkUtils(getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimeline(txtCategoryName.getText().toString());
            }
        });

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        bgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListPosts("list","");
            }
        });
        txtCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListPosts("list","");
            }
        });

        return view;
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");

        txtCategoryName.setTypeface(Roboto_Regular);
    }

    public void setCategories()
    {
        ListCategories=new ArrayList<>();
        ListCategories.add("Finance");
        ListCategories.add("Skills");
        ListCategories.add("Facilities");
        ListCategories.add("Opportunities");
        ListCategories.add("Courses");
        adapterCategories=new RecycleViewAdapterFeedsCategories(ListCategories,getActivity(),this);
        recyclerView.setAdapter(adapterCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setListPosts(String status,String category)
    {
        if(status.equalsIgnoreCase("detail"))
        {
            recyclerView.setVisibility(View.GONE);
            recyclerView2.setVisibility(View.VISIBLE);
            bgCategory.setVisibility(View.VISIBLE);
            txtCategoryName.setVisibility(View.VISIBLE);
            expand.setVisibility(View.VISIBLE);
            fetchTimeline(category);
            txtCategoryName.setText(category);
            switch (category)
            {
                case "Finances":
                {
                    bgCategory.setBackgroundResource(R.drawable.bg_finances);
                    break;
                }
                case "Courses":
                {
                    bgCategory.setBackgroundResource(R.drawable.bg_finances);
                    break;
                }
                case "Skills":
                {
                    bgCategory.setBackgroundResource(R.drawable.bg_skills);
                    break;
                }
                case "Facilities" :
                {
                    bgCategory.setBackgroundResource(R.drawable.bg_facilities);
                    break;
                }
                case "Oppotunities" :
                {
                    bgCategory.setBackgroundResource(R.drawable.bg_finances);
                    break;
                }
            }
        }
        if(status.equalsIgnoreCase("list"))
        {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView2.setVisibility(View.GONE);
            bgCategory.setVisibility(View.GONE);
            txtCategoryName.setVisibility(View.GONE);
            expand.setVisibility(View.GONE);
        }
    }

    public void fetchTimeline(String category)
    {
        if(network.isConnected())
        {
            TC.getTimelineByCategory(category);
        }
        else
        {
            Toast.makeText(getActivity(), "phone is not connected to internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getTimelineByCategoryResponse(boolean error, TimelineResponse response, Throwable t) {
        if(!error)
        {
            adapterPosts=new RecycleViewAdapterListPost(response.posts,getContext());
            recyclerView2.setAdapter(adapterPosts);
            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}
