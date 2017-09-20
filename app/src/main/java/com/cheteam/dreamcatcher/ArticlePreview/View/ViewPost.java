package com.cheteam.dreamcatcher.ArticlePreview.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.Controller.ArticleController;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
import com.cheteam.dreamcatcher.CommentSection.View.MainComment;
import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rahmat Al Hakam on 14/09/2017.
 */

public class ViewPost extends AppCompatActivity implements ArticleController.onViewArticleResponse{
    @BindView(R.id.bg_view_post) ImageView bg_view_post;
    @BindView(R.id.avatar_vp) CircleImageView avatar_vp;
    @BindView(R.id.tv_name_vp) TextView tv_name_vp;
    @BindView(R.id.tv_title_view_post) TextView tv_title_view_post;
    @BindView(R.id.tv_date_vp) TextView tv_date_vp;
    @BindView(R.id.tv_content_vp) TextView tv_content_vp;
    @BindView(R.id.toolbar_view_post) Toolbar myToolbar;
    @BindView(R.id.bookmark_icon_vp) ImageView bookmark_icon_vp;
    Boolean cek=false;
    ArticleController AC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("View Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        AC=new ArticleController(this);
        AC.GetArticle(1);
        bookmark_icon_vp.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);

        bookmark_icon_vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cek)
                {
                    cek=true;
                    bookmark_icon_vp.setBackgroundResource(R.drawable.icon_bookmark);
                }
                else if(cek)
                {
                    cek=false;
                    bookmark_icon_vp.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home: onBackPressed();
                break;
            case R.id.comment_vp:
                Intent intent= new Intent(this, MainComment.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_post, menu);
        return true;
    }

    @Override
    public void getViewArticleResponse(boolean error, ViewArticleResponse response, Throwable t) {
        if(!error){
            ViewArticleResponse model = response;
            tv_name_vp.setText(model.name);
            tv_title_view_post.setText(model.post_title);
            tv_date_vp.setText(model.published_at);
            tv_content_vp.setText(model.content);
            if(model.id_background==1){
                bg_view_post.setBackgroundResource(R.drawable.red_bg);
            }
            if(model.id_background==2){
                bg_view_post.setBackgroundResource(R.drawable.green_bg);
            }
            if(model.id_background==3){
                bg_view_post.setBackgroundResource(R.drawable.blue_bg);
            }
            if(model.id_background==4){
                bg_view_post.setBackgroundResource(R.drawable.yellow_bg);
            }
            if(model.id_background==5){
                bg_view_post.setBackgroundResource(R.drawable.violet_bg);
            }
            if(model.id_avatar==0){
                avatar_vp.setBackgroundResource(R.drawable.avatar_0);
            }
            if(model.id_avatar==1){
                avatar_vp.setBackgroundResource(R.drawable.avatar_1);
            }
            if(model.id_avatar==2){
                avatar_vp.setBackgroundResource(R.drawable.avatar_2);
            }
            if(model.id_avatar==3){
                avatar_vp.setBackgroundResource(R.drawable.avatar_3);
            }
            if(model.id_avatar==4){
                avatar_vp.setBackgroundResource(R.drawable.avatar_4);
            }
            if(model.id_avatar==5){
                avatar_vp.setBackgroundResource(R.drawable.avatar_5);
            }
            if(model.id_avatar==6){
                avatar_vp.setBackgroundResource(R.drawable.avatar_6);
            }
            if(model.id_avatar==7){
                avatar_vp.setBackgroundResource(R.drawable.avatar_7);
            }
            if(model.id_avatar==8){
                avatar_vp.setBackgroundResource(R.drawable.avatar_8);
            }
            if(model.id_avatar==9){
                avatar_vp.setBackgroundResource(R.drawable.avatar_9);
            }
        }
    }


}
