package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Fragment.FragmentFeedsCategory;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class RecycleViewAdapterFeedsCategories extends RecyclerView.Adapter<RecycleViewAdapterFeedsCategories.ViewHolder> {

    List<String> list = Collections.emptyList();
    Context context;
    FragmentFeedsCategory fragment;


    public View view;

    public RecycleViewAdapterFeedsCategories(List<String> list, Context context, FragmentFeedsCategory fragment) {
        this.context = context;
        this.list = list;
        this.fragment=fragment;
    }


    @Override
    public RecycleViewAdapterFeedsCategories.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categories_feeds_layout, parent, false);
        RecycleViewAdapterFeedsCategories.ViewHolder holder = new RecycleViewAdapterFeedsCategories.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterFeedsCategories.ViewHolder holder, int position) {
        Typeface Merriweather_Bold=Typeface.createFromAsset(context.getAssets(), "fonts/Merriweather-Bold.ttf");
        Typeface Lobster_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF=Typeface.createFromAsset(context.getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtCategoryName.setTypeface(Roboto_Regular);

        holder.txtCategoryName.setText(list.get(position));
        if(list.get(position).equalsIgnoreCase("Finance"))
        {
            holder.bgCategory.setBackgroundResource(R.drawable.bg_finances);
        }
        if(list.get(position).equalsIgnoreCase("Skills"))
        {
            holder.bgCategory.setBackgroundResource(R.drawable.bg_skills);
        }
        if(list.get(position).equalsIgnoreCase("Facilities"))
        {
            holder.bgCategory.setBackgroundResource(R.drawable.bg_facilities);
        }
        if(list.get(position).equalsIgnoreCase("Opportunities"))
        {
            holder.bgCategory.setBackgroundResource(R.drawable.bg_opportunities);
        }
        if(list.get(position).equalsIgnoreCase("Courses"))
        {
            holder.bgCategory.setBackgroundResource(R.drawable.bg_courses);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtCategoryName) TextView txtCategoryName;
        @BindView(R.id.bgCategory) ImageView bgCategory;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);

            txtCategoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.setListPosts("detail",txtCategoryName.getText().toString());
                }
            });
            bgCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.setListPosts("detail",txtCategoryName.getText().toString());
                }
            });
        }
    }
}