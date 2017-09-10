package com.cheteam.dreamcatcher.ArticlePreview.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.Controller.ViewArticleApi;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ArticleModel;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


/**
 * Created by Rahmat Al Hakam on 09/09/2017.
 */

public class ActivityPreview extends AppCompatActivity{
    TextView tv_title_preview,tv_name_preview, preview_category, preview_tanggal,tv_content_preview,tv_category_preview;
    CircleImageView preview_avatar_user;
    ImageView bg_preview;
    ViewArticleApi service;
    ImageView bookmark_icon;
    Boolean cek=false;

    Call<ArticleModel> CallArticle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_preview);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_article_preview);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Preview");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        tv_title_preview = (TextView) findViewById(R.id.tv_title_preview);
        tv_name_preview = (TextView) findViewById(R.id.tv_name_preview);
        preview_category = (TextView) findViewById(R.id.preview_category);
        preview_tanggal = (TextView) findViewById(R.id.preview_tanggal);
        tv_content_preview = (TextView) findViewById(R.id.tv_content_preview);
        tv_category_preview = (TextView) findViewById(R.id.tv_category_preview);
        preview_avatar_user = (CircleImageView) findViewById(R.id.preview_avatar_user);
        bg_preview = (ImageView) findViewById(R.id.bg_preview);
        bookmark_icon = (ImageView) findViewById(R.id.bookmark_icon);
        setArticle(1);

        bookmark_icon.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);

        bookmark_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cek)
                {
                    cek=true;
                    bookmark_icon.setBackgroundResource(R.drawable.icon_bookmark);
                }
                else if(cek)
                {
                    cek=false;
                    bookmark_icon.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                }
            }
        });


    }

    public void setArticle(int id_post)
    {
        service= ServiceGenerator.createService(ViewArticleApi.class);
        CallArticle=service.getArticle(id_post);
        CallArticle.enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Response<ArticleModel> response) {
                ArticleModel model=response.body();
                tv_title_preview.setText(model.post_title);
                tv_name_preview.setText(model.name);
                preview_category.setText("in");
                tv_category_preview.setText(model.categories);
                preview_tanggal.setText(model.published_at);
                tv_content_preview.setText(model.content);
                if(model.id_background==1){
                    bg_preview.setBackgroundResource(R.drawable.red_bg);
                }
                if(model.id_background==2){
                    bg_preview.setBackgroundResource(R.drawable.green_bg);
                }
                if(model.id_background==3){
                    bg_preview.setBackgroundResource(R.drawable.blue_bg);
                }
                if(model.id_background==4){
                    bg_preview.setBackgroundResource(R.drawable.yellow_bg);
                }
                if(model.id_background==5){
                    bg_preview.setBackgroundResource(R.drawable.violet_bg);
                }

                if(model.id_avatar==0){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_0);
                }
                if(model.id_avatar==1){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_1);
                }
                if(model.id_avatar==2){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_2);
                }
                if(model.id_avatar==3){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_3);
                }
                if(model.id_avatar==4){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_4);
                }
                if(model.id_avatar==5){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_5);
                }
                if(model.id_avatar==6){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_6);
                }
                if(model.id_avatar==7){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_7);
                }
                if(model.id_avatar==8){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_8);
                }
                if(model.id_avatar==9){
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_9);
                }

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_article_preview, menu);

        return true;
    }
}
