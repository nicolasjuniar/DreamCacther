package com.cheteam.dreamcatcher.ArticlePreview.View;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.API.ViewArticleApi;
import com.cheteam.dreamcatcher.ArticlePreview.Controller.ArticleController;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
import com.cheteam.dreamcatcher.CommentSection.View.MainComment;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rahmat Al Hakam on 09/09/2017.
 */

public class ArticlePreview extends AppCompatActivity implements ArticleController.onViewArticleResponse {
    @BindView(R.id.tv_title_article) TextView tv_title_article;
    @BindView(R.id.tv_name) TextView tv_name;
    @BindView(R.id.tanggal_post) TextView tanggal_post;
    @BindView(R.id.tv_content_article) TextView tv_content_article;
    @BindView(R.id.AvatarUser) CircleImageView AvatarUser;
    @BindView(R.id.bg_post) ImageView bg_post;
    @BindView(R.id.my_toolbar_article_layout) Toolbar myToolbar;
    ArticleController AC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_layout);

        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("View Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        AC=new ArticleController(this);
        AC.GetArticle(1);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article_layout, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.comment_action_bar)
        {
            Intent intent=new Intent(ArticlePreview.this,MainComment.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getViewArticleResponse(boolean error, ViewArticleResponse response, Throwable t) {
        if(!error)
        {
            ViewArticleResponse model = response;
            tv_title_article.setText(model.post_title);
            tv_name.setText(model.name);
            tanggal_post.setText(model.published_at);
            tv_content_article.setText(model.content);
            if(model.id_background==1){
                bg_post.setBackgroundResource(R.drawable.red_bg);
            }
            if(model.id_background==2){
                bg_post.setBackgroundResource(R.drawable.green_bg);
            }
            if(model.id_background==3){
                bg_post.setBackgroundResource(R.drawable.blue_bg);
            }
            if(model.id_background==4){
                bg_post.setBackgroundResource(R.drawable.yellow_bg);
            }
            if(model.id_background==5){
                bg_post.setBackgroundResource(R.drawable.violet_bg);
            }
            if(model.id_avatar==0){
                AvatarUser.setBackgroundResource(R.drawable.avatar_0);
            }
            if(model.id_avatar==1){
                AvatarUser.setBackgroundResource(R.drawable.avatar_1);
            }
            if(model.id_avatar==2){
                AvatarUser.setBackgroundResource(R.drawable.avatar_2);
            }
            if(model.id_avatar==3){
                AvatarUser.setBackgroundResource(R.drawable.avatar_3);
            }
            if(model.id_avatar==4){
                AvatarUser.setBackgroundResource(R.drawable.avatar_4);
            }
            if(model.id_avatar==5){
                AvatarUser.setBackgroundResource(R.drawable.avatar_5);
            }
            if(model.id_avatar==6){
                AvatarUser.setBackgroundResource(R.drawable.avatar_6);
            }
            if(model.id_avatar==7){
                AvatarUser.setBackgroundResource(R.drawable.avatar_7);
            }
            if(model.id_avatar==8){
                AvatarUser.setBackgroundResource(R.drawable.avatar_8);
            }
            if(model.id_avatar==9){
                AvatarUser.setBackgroundResource(R.drawable.avatar_9);
            }
        }
    }
}
