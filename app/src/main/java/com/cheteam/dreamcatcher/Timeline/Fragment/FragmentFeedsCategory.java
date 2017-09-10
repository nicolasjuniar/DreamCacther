package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterFeedsCategories;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterListPost;
import com.cheteam.dreamcatcher.Timeline.Controller.TimelineAPI;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.Timeline.Model.ResponseTimeline;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 09/09/2017.
 */

public class FragmentFeedsCategory extends Fragment {

    ArrayList<String> ListCategories;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecycleViewAdapterFeedsCategories adapterCategories;
    RecycleViewAdapterListPost adapterPosts;
    ImageView bgCategory,expand;
    TextView txtCategoryName;

    TimelineAPI service;
    ArrayList<ModelTimeline> list;
    Call<ResponseTimeline> CallTimeline;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_categories_layout,
                container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.ListCategories);
        recyclerView2=(RecyclerView) view.findViewById(R.id.ListPost);
        bgCategory=(ImageView) view.findViewById(R.id.bgCategory);
        expand=(ImageView) view.findViewById(R.id.expand);
        txtCategoryName=(TextView) view.findViewById(R.id.txtCategoryName);
        setCategories();
        recyclerView2.setVisibility(View.GONE);
        bgCategory.setVisibility(View.GONE);
        txtCategoryName.setVisibility(View.GONE);
        setFont();

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
        Typeface Lobster_Regular=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lobster-Regular.ttf");

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
            setPosts();
            if(category.equalsIgnoreCase("Finance"))
            {
                txtCategoryName.setText("Finance");
                bgCategory.setBackgroundResource(R.drawable.bg_finances);
            }
            if(category.equalsIgnoreCase("Courses"))
            {
                txtCategoryName.setText("Courses");
                bgCategory.setBackgroundResource(R.drawable.bg_finances);
            }
            if(category.equalsIgnoreCase("Skills"))
            {
                txtCategoryName.setText("Skills");
                bgCategory.setBackgroundResource(R.drawable.bg_skills);
            }
            if(category.equalsIgnoreCase("Facilities"))
            {
                txtCategoryName.setText("Facilities");
                bgCategory.setBackgroundResource(R.drawable.bg_facilities);
            }
            if(category.equalsIgnoreCase("Opportunities"))
            {
                txtCategoryName.setText("Opportunities");
                bgCategory.setBackgroundResource(R.drawable.bg_finances);
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


    public void setPosts()
    {
        service= ServiceGenerator.createService(TimelineAPI.class);
        CallTimeline=service.GetTimeline();
        CallTimeline.enqueue(new Callback<ResponseTimeline>() {
            @Override
            public void onResponse(Response<ResponseTimeline> response) {
                list=response.body().posts;
                adapterPosts=new RecycleViewAdapterListPost(list,getContext());
                recyclerView2.setAdapter(adapterPosts);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
