package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheteam.dreamcatcher.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by MPR on 9/9/2017.
 */

public class RecyclerViewAdapterListAvatar extends RecyclerView.Adapter<RecyclerViewAdapterListAvatar.BookmarksViewHolder>{

    List<Integer> ListAvatar = Collections.emptyList();
    Context context;
    View view;

    public RecyclerViewAdapterListAvatar(List<Integer> ListAvatar, Context context) {
        this.context = context;
        this.ListAvatar = ListAvatar;
    }

    @Override
    public BookmarksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pick_avatar_layout, parent, false);
        BookmarksViewHolder holder = new BookmarksViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BookmarksViewHolder holder, int position) {
        int id_avatar=ListAvatar.get(position);
        if(id_avatar==1)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_1);
        }
        if(id_avatar==2)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_2);
        }
        if(id_avatar==3)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_3);
        }
        if(id_avatar==4)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_4);
        }
        if(id_avatar==5)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_5);
        }
        if(id_avatar==6)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_6);
        }
        if(id_avatar==7)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_7);
        }
        if(id_avatar==8)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_8);
        }
        if(id_avatar==9)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_9);
        }
    }

    @Override
    public int getItemCount() {
        return ListAvatar.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class BookmarksViewHolder extends RecyclerView.ViewHolder {

        ImageView AvatarUser;

        BookmarksViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            AvatarUser=(ImageView) view.findViewById(R.id.AvatarUser);
            AvatarUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity)context).finish();
                }
            });
        }
    }
}
