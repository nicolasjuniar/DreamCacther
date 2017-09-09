package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.SplashScreenActivity;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class RecycleViewAdapterListPost extends RecyclerView.Adapter<RecycleViewAdapterListPost.ViewHolder> {

    List<ModelTimeline> list = Collections.emptyList();
    Context context;


    public View view;

    public RecycleViewAdapterListPost(List<ModelTimeline> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecycleViewAdapterListPost.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_timeline_item, parent, false);
        RecycleViewAdapterListPost.ViewHolder holder = new RecycleViewAdapterListPost.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterListPost.ViewHolder holder, int position) {
        Typeface Merriweather_Bold=Typeface.createFromAsset(context.getAssets(), "fonts/Merriweather-Bold.ttf");
        Typeface Lobster_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF=Typeface.createFromAsset(context.getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtTitle.setTypeface(Roboto_Regular);
        holder.txtFullName.setTypeface(Roboto_Regular);
        holder.txtTime.setTypeface(Roboto_Regular);
        holder.txtCategories.setTypeface(Roboto_Regular);


        ModelTimeline model=list.get(position);
        holder.txtTitle.setText(model.post_title);
        holder.txtFullName.setText(model.name);
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
        if(model.id_avatar==1)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_1);
        }
        if(model.id_avatar==2)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_2);
        }
        if(model.id_avatar==3)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_3);
        }
        if(model.id_avatar==4)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_4);
        }
        if(model.id_avatar==5)
        {
            holder.AvatarUser.setImageResource(R.drawable.avatar_5);
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

        TextView txtTitle,txtTime, txtFullName;
        CircleImageView AvatarUser;
        ImageView BgImage;
        TextView txtCategories;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTime=(TextView) view.findViewById(R.id.txtTime);
            txtTitle=(TextView) view.findViewById(R.id.txtTitle);
            txtFullName=(TextView) view.findViewById(R.id.txtFullName);
            txtCategories=(TextView) view.findViewById(R.id.txtCategories);
            AvatarUser=(CircleImageView) view.findViewById(R.id.AvatarUser);
            BgImage=(ImageView) view.findViewById(R.id.BgImage);
        }
    }
}