package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.View.ArticlePreview;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RecyclerViewAdapterMypost extends RecyclerView.Adapter<RecyclerViewAdapterMypost.MypostViewHolder> {

    List<ModelTimeline> postlist = Collections.emptyList();
    Context context;

    public View view;

    public RecyclerViewAdapterMypost(List<ModelTimeline> postlist, Context context) {
        this.context = context;
        this.postlist = postlist;
    }


    @Override
    public MypostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_profile_item, parent, false);
        MypostViewHolder holder = new MypostViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MypostViewHolder holder, int position) {
        Typeface Merriweather_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Merriweather-Bold.ttf");
        Typeface Lobster_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF = Typeface.createFromAsset(context.getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtTitle.setTypeface(Roboto_Regular);
        holder.txtTime.setTypeface(Roboto_Regular);
        holder.txtCategories.setTypeface(Roboto_Regular);

        ModelTimeline model = postlist.get(position);
        holder.txtTitle.setText(model.post_title);
        holder.txtTime.setText(model.published_at);
        holder.txtCategories.setText(model.categories);
        if(model.id_background==1)
        {
            holder.BgImage.setBackgroundResource(R.drawable.red_bg);
        }
        if(model.id_background==2)
        {
            holder.BgImage.setBackgroundResource(R.drawable.green_bg);
        }
        if(model.id_background==3)
        {
            holder.BgImage.setBackgroundResource(R.drawable.blue_bg);
        }
        if(model.id_background==4)
        {
            holder.BgImage.setBackgroundResource(R.drawable.yellow_bg);
        }
        if(model.id_background==5)
        {
            holder.BgImage.setBackgroundResource(R.drawable.violet_bg);
        }
    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class MypostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_post_title) TextView txtTitle;
        @BindView(R.id.posttime) TextView txtTime;
        @BindView(R.id.categories) TextView txtCategories;
        @BindView(R.id.BgImage) ImageView BgImage;

        MypostViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myactivity = new Intent(context, ArticlePreview.class);
                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(myactivity);
                }
            });
        }
    }
}