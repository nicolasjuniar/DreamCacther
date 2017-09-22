package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MPR on 9/9/2017.
 */

public class RecyclerViewAdapterListCover extends RecyclerView.Adapter<RecyclerViewAdapterListCover.BookmarksViewHolder>{

    List<String> ListCover = Collections.emptyList();
    Context context;
    View view;
    int selectedItem =-1;

    public RecyclerViewAdapterListCover(List<String> ListCover, Context context) {
        this.context = context;
        this.ListCover = ListCover;
    }

    @Override
    public BookmarksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pick_cover_item, parent, false);
        BookmarksViewHolder holder = new BookmarksViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BookmarksViewHolder holder, final int position) {
        final String id_cover=ListCover.get(position);
        if (selectedItem==position) {
            holder.coverHighlight.setSelected(true);
        }else {
            holder.coverHighlight.setSelected(false);
        }
        holder.bgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem=position;
                notifyDataSetChanged();
            }
        });
        if(id_cover.equalsIgnoreCase("1"))
        {
            holder.bgCover.setBackgroundResource(R.drawable.cover_6);
        }
        if(id_cover.equalsIgnoreCase("2"))
        {
            holder.bgCover.setBackgroundResource(R.drawable.cover_7);
        }
        if(id_cover.equalsIgnoreCase("3"))
        {
            holder.bgCover.setBackgroundResource(R.drawable.cover_8);
        }
        if(id_cover.equalsIgnoreCase("4"))
        {
            holder.bgCover.setBackgroundResource(R.drawable.cover_9);
        }
        if(id_cover.equalsIgnoreCase("5"))
        {
            holder.bgCover.setBackgroundResource(R.drawable.cover_10);
        }
    }

    @Override
    public int getItemCount() {
        return ListCover.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class BookmarksViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bgCover) ImageView bgCover;
        @BindView(R.id.CoverHighlight)LinearLayout coverHighlight;

        BookmarksViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);

        }
    }
}
