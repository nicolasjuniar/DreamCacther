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
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class RecycleViewAdapterListCategories extends RecyclerView.Adapter<RecycleViewAdapterListCategories.ViewHolder> {

    List<String> list = Collections.emptyList();
    Context context;


    public View view;

    public RecycleViewAdapterListCategories(List<String> list, Context context) {
        this.context = context;
        this.list = list;
    }

    public void setListCategories(List<String> list) {
        this.list=list;
    }

    @Override
    public RecycleViewAdapterListCategories.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categories_item, parent, false);
        RecycleViewAdapterListCategories.ViewHolder holder = new RecycleViewAdapterListCategories.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterListCategories.ViewHolder holder, int position) {
        Typeface Roboto_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtCategories.setTypeface(Roboto_Regular);

        holder.txtCategories.setText(list.get(position));
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

        @BindView(R.id.txtCategories) TextView txtCategories;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);
        }
    }
}