package com.cheteam.dreamcatcher.CommentSection.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.CommentSection.Model.CommentModel;
import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rahmat Al Hakam on 18/09/2017.
 */

public class RecycleViewAdapterComment extends RecyclerView.Adapter<RecycleViewAdapterComment.CommentViewHolder>{

    private CommentModel mCommentModel;

    public RecycleViewAdapterComment(CommentModel commentModel) {
        mCommentModel = commentModel;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_comment_item,parent,false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.tv_content_com.setText(mCommentModel.comment_title);
        holder.tv_date_com.setText(mCommentModel.published_at);
        holder.tv_name_com.setText(""+mCommentModel.id_user);
        if(mCommentModel.id_user==0)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_0);
        if(mCommentModel.id_user==1)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_1);
        if(mCommentModel.id_user==2)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_2);
        if(mCommentModel.id_user==3)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_3);
        if(mCommentModel.id_user==4)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_4);
        if(mCommentModel.id_user==5)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_5);
        if(mCommentModel.id_user==6)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_6);
        if(mCommentModel.id_user==7)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_7);
        if(mCommentModel.id_user==8)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_8);
        if(mCommentModel.id_user==10)
            holder.iv_avatar_com.setImageResource(R.drawable.avatar_9);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_content_com) TextView tv_content_com;
        @BindView(R.id.iv_avatar_com) ImageView iv_avatar_com;
        @BindView(R.id.tv_name_com) TextView tv_name_com;
        @BindView(R.id.tv_date_comm) TextView tv_date_com;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
