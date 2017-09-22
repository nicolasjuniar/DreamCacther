package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.View.ArticlePreview;
import com.cheteam.dreamcatcher.ArticlePreview.View.ViewPost;
import com.cheteam.dreamcatcher.SplashScreenActivity;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.R;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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
        Typeface Roboto_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtTitle.setTypeface(Roboto_Regular);
        holder.txtFullName.setTypeface(Roboto_Regular);
        holder.txtTime.setTypeface(Roboto_Regular);
        holder.txtCategories.setTypeface(Roboto_Regular);


        ModelTimeline model=list.get(position);
        holder.txtTitle.setText(model.post_title);
        holder.txtFullName.setText(model.name);
        holder.txtCategories.setText(model.categories);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date past = format.parse(model.published_at);
            Date now = new Date();

            String ms=TimeUnit.MILLISECONDS.toMillis(now.getTime() - past.getTime()) + " milliseconds ago";
            ms=TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago";
            ms=TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago";
            ms=TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago";
            holder.txtTime.setText(ms);
        }
        catch (Exception j){
            j.printStackTrace();
        }
        holder.BgImage.setScaleType(ImageView.ScaleType.FIT_XY);
        switch(model.id_background)
        {
            case 1:
            {
                holder.BgImage.setImageResource(R.drawable.red_bg);
                break;
            }
            case 2:
            {
                holder.BgImage.setImageResource(R.drawable.green_bg);
                break;
            }
            case 3:
            {
                holder.BgImage.setImageResource(R.drawable.blue_bg);
                break;
            }
            case 4:
            {
                holder.BgImage.setImageResource(R.drawable.violet_bg);
                break;
            }
            case 5:
            {
                holder.BgImage.setImageResource(R.drawable.violet_bg);
                break;
            }
        }

        switch (model.id_avatar)
        {
            case 0:
            {
                holder.AvatarUser.setImageResource(R.drawable.avatar_0);
                break;
            }
            case 1:
            {
                holder.AvatarUser.setImageResource(R.drawable.avatar_1);
                break;
            }
            case 2:
            {
                holder.AvatarUser.setImageResource(R.drawable.avatar_2);
                break;
            }
            case 3:
            {
                holder.AvatarUser.setImageResource(R.drawable.avatar_3);
                break;
            }
            case 4:
            {
                holder.AvatarUser.setImageResource(R.drawable.avatar_4);
                break;
            }
            case 5:
            {
                holder.AvatarUser.setImageResource(R.drawable.avatar_5);
                break;
            }
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

        @BindView(R.id.txtTitle) TextView txtTitle;
        @BindView(R.id.txtTime) TextView txtTime;
        @BindView(R.id.txtFullName) TextView txtFullName;
        @BindView(R.id.AvatarUser) CircleImageView AvatarUser;
        @BindView(R.id.BgImage) ImageView BgImage;
        @BindView(R.id.txtCategories) TextView txtCategories;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myactivity = new Intent(context, ViewPost.class);
                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(myactivity);
                }
            });
        }
    }
}