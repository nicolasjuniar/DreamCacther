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

import com.cheteam.dreamcatcher.ArticlePreview.View.ViewPost;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.Timeline.View.OtherUserActivity;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by MPR on 9/9/2017.
 */

public class RecyclerViewAdapterBookmarks extends RecyclerView.Adapter<RecyclerViewAdapterBookmarks.BookmarksViewHolder>{

    List<ModelTimeline> bookmarklist = Collections.emptyList();
    Context context;
    View view;

    public RecyclerViewAdapterBookmarks(List<ModelTimeline> bookmarklist, Context context) {
        this.context = context;
        this.bookmarklist = bookmarklist;
    }

    @Override
    public BookmarksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bookmark_item, parent, false);
        BookmarksViewHolder holder = new BookmarksViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BookmarksViewHolder holder, int position) {
        Typeface Merriweather_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Merriweather-Bold.ttf");
        Typeface Lobster_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF = Typeface.createFromAsset(context.getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtTitle.setTypeface(Roboto_Regular);
        holder.txtFullName.setTypeface(Roboto_Regular);
        holder.txtCategories.setTypeface(Roboto_Regular);

        ModelTimeline model = bookmarklist.get(position);
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
        return bookmarklist.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class BookmarksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_post_title) TextView txtTitle;
        @BindView(R.id.fullname) TextView txtFullName;
        @BindView(R.id.categories) TextView txtCategories;
        @BindView(R.id.AvatarUser) CircleImageView AvatarUser;
        @BindView(R.id.BgImage) ImageView BgImage;

        BookmarksViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);
            BgImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myactivity = new Intent(context, ViewPost.class);
                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(myactivity);
                }
            });
            AvatarUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fragment = new Intent(context,OtherUserActivity.class);
                    fragment.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(fragment);
                }
            });
        }
    }
}
