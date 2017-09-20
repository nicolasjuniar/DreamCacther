package com.cheteam.dreamcatcher.InterestForm.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheteam.dreamcatcher.InterestForm.Model.ModelInterest;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class RecycleViewAdapterListInterest extends RecyclerView.Adapter<RecycleViewAdapterListInterest.ViewHolder> {

    List<ModelInterest> list = Collections.emptyList();
    Context context;
    public View view;

    onChangeInterest listener;

    public RecycleViewAdapterListInterest(List<ModelInterest> list, Context context, onChangeInterest listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }


    @Override
    public RecycleViewAdapterListInterest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_interest_item, parent, false);
        RecycleViewAdapterListInterest.ViewHolder holder = new RecycleViewAdapterListInterest.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterListInterest.ViewHolder holder, int position) {
        Typeface RockoFLF=Typeface.createFromAsset(context.getAssets(), "fonts/RockoFLF.ttf");
        holder.txtInterest.setTypeface(RockoFLF);

        ModelInterest model=list.get(position);
        holder.txtInterest.setText(model.interest);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface onChangeInterest
    {
        void addInterest(String interest);
        void removeInterest(String interest);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtInterest) TextView txtInterest;
        boolean cek;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this,view);
            cek=false;

            txtInterest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!cek)
                    {
                        cek=true;
                        txtInterest.setTextColor(context.getResources().getColor(R.color.colar));
                        listener.addInterest(txtInterest.getText().toString());
                    }
                    else if(cek)
                    {
                        cek=false;
                        txtInterest.setTextColor(context.getResources().getColor(R.color.duckEggBlue));
                        listener.removeInterest(txtInterest.getText().toString());
                    }

                }
            });
        }
    }
}