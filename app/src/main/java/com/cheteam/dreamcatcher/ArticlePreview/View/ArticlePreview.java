package com.cheteam.dreamcatcher.ArticlePreview.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.AddPost.View.EditPostActivity;
import com.cheteam.dreamcatcher.ArticlePreview.Controller.ArticleController;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
import com.cheteam.dreamcatcher.CommentSection.View.MainComment;
import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rahmat Al Hakam on 09/09/2017.
 * Layout untuk OWN POST dan POP UP
 */

public class ArticlePreview extends AppCompatActivity implements ArticleController.onViewArticleResponse {
    @BindView(R.id.tv_title_article) TextView tv_title_article;
    @BindView(R.id.tv_name) TextView tv_name;
    @BindView(R.id.tanggal_post) TextView tanggal_post;
    @BindView(R.id.tv_content_article) TextView tv_content_article;
    @BindView(R.id.AvatarUser) CircleImageView AvatarUser;
    @BindView(R.id.bg_post) ImageView bg_post;
    @BindView(R.id.my_toolbar_article_layout) Toolbar myToolbar;
    @BindView(R.id.tv_category_av) TextView tv_category_av;
    ArticleController mArticleController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_layout);

        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("View Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        mArticleController =new ArticleController(this);
        mArticleController.GetArticle(1);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_layout, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.comment_action_bar)
        {
            Intent intent=new Intent(ArticlePreview.this,MainComment.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.edit_action_bar){
            Intent intent=new Intent(this, EditPostActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.delete_action_bar){
            onBackPressed();
        }
        else if(id==android.R.id.home){
            onBackPressed();
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
            tv_category_av.setText(model.categories);
            switch (model.id_background){
                case 1:
                    bg_post.setBackgroundResource(R.drawable.red_bg);
                    break;
                case 2:
                    bg_post.setBackgroundResource(R.drawable.green_bg);
                    break;
                case 3:
                    bg_post.setBackgroundResource(R.drawable.blue_bg);
                    break;
                case 4:
                    bg_post.setBackgroundResource(R.drawable.yellow_bg);
                    break;
                case 5:
                    bg_post.setBackgroundResource(R.drawable.violet_bg);
                    break;
            }
            switch (model.id_avatar){
                case 0:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_0);
                    break;
                case 1:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_1);
                    break;
                case 2:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_2);
                    break;
                case 3:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_3);
                    break;
                case 4:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_4);
                    break;
                case 5:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_5);
                    break;
                case 6:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_6);
                    break;
                case 7:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_7);
                    break;
                case 8:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_8);
                    break;
                case 9:
                    AvatarUser.setBackgroundResource(R.drawable.avatar_9);
                    break;
            }
        }
    }
}
