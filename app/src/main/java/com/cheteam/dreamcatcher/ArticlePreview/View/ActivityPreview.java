package com.cheteam.dreamcatcher.ArticlePreview.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.AddPost.View.AddPostActivity;
import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostRequest;
import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostResponse;
import com.cheteam.dreamcatcher.ArticlePreview.Controller.ArticleController;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
import com.cheteam.dreamcatcher.CommentSection.View.MainComment;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Rahmat Al Hakam on 09/09/2017.
 * Layout untuk PREVIEW SUBMIT
 */

public class ActivityPreview extends AppCompatActivity implements ArticleController.onAddPostRequest{
    @BindView(R.id.tv_title_preview)
    TextView tv_title_preview;
    @BindView(R.id.tv_name_preview)
    TextView tv_name_preview;
    @BindView(R.id.preview_category)
    TextView preview_category;
    @BindView(R.id.preview_tanggal)
    TextView preview_tanggal;
    @BindView(R.id.tv_content_preview)
    TextView tv_content_preview;
    @BindView(R.id.preview_avatar_user)
    CircleImageView preview_avatar_user;
    @BindView(R.id.bg_preview)
    ImageView bg_preview;
    @BindView(R.id.my_toolbar_article_preview)
    Toolbar myToolbar;

    ArticleController mArticleController;

    Bundle bundle;
    int id_user;
    int id_background;
    String post_title;
    String categories;
    String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_preview);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Preview");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        mArticleController = new ArticleController(this);

        //mArticleController.GetArticle(1);
        bundle= getIntent().getExtras();
        id_user = bundle.getInt("id_user");
        id_background = bundle.getInt("id_background");
        post_title = bundle.getString("post_title");
        categories = bundle.getString("categories");
        content = bundle.getString("content");

        tv_title_preview.setText(post_title);
        tv_name_preview.setText(R.string.Mae_Spancer); ///Belum FIX
        preview_category.setText(categories);
        tv_content_preview.setText(content);
        switch (id_background) {
            case 1:
                bg_preview.setBackgroundResource(R.drawable.red_bg);
                break;
            case 2:
                bg_preview.setBackgroundResource(R.drawable.green_bg);
                break;
            case 3:
                bg_preview.setBackgroundResource(R.drawable.blue_bg);
                break;
            case 4:
                bg_preview.setBackgroundResource(R.drawable.yellow_bg);
                break;
            case 5:
                bg_preview.setBackgroundResource(R.drawable.violet_bg);
                break;
        }
        switch (id_user) {
                case 0:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_0);
                    break;
                case 1:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_1);
                    break;
                case 2:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_2);
                    break;
                case 3:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_3);
                    break;
                case 4:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_4);
                    break;
                case 5:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_5);
                    break;
                case 6:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_6);
                    break;
                case 7:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_7);
                    break;
                case 8:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_8);
                    break;
                case 9:
                    preview_avatar_user.setBackgroundResource(R.drawable.avatar_9);
                    break;
            }




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.app_changes:

                mArticleController.addPost(new AddPostRequest(
                        id_user,
                        id_background,
                        post_title,
                        categories,
                        content
                 ), "iu6JrrCVyoaJNrl0dhAt");
                Intent intent= new Intent(this, TimelineActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_article_preview, menu);

        return true;
    }

    @Override
    public void onAddPostRequest(boolean error, AddPostRequest response, Throwable t) {
        if(!error){
            Toast.makeText(this, new Gson().toJson(response), Toast.LENGTH_SHORT).show();
//            tv_title_preview.setText(post_title);
//            tv_name_preview.setText(R.string.Mae_Spancer); ///Belum FIX
//            preview_category.setText(categories);
//            tv_content_preview.setText(content);
//            switch (id_background) {
//                case 1:
//                    bg_preview.setBackgroundResource(R.drawable.red_bg);
//                    break;
//                case 2:
//                    bg_preview.setBackgroundResource(R.drawable.green_bg);
//                    break;
//                case 3:
//                    bg_preview.setBackgroundResource(R.drawable.blue_bg);
//                    break;
//                case 4:
//                    bg_preview.setBackgroundResource(R.drawable.yellow_bg);
//                    break;
//                case 5:
//                    bg_preview.setBackgroundResource(R.drawable.violet_bg);
//                    break;
            }
        }
}

    //
//    @Override
//    public void getViewArticleResponse(boolean error, ViewArticleResponse response, Throwable t) {
//        if (!error) {
//            ViewArticleResponse model = response;
//            tv_title_preview.setText(model.post_title);
//            tv_name_preview.setText(model.name);
//            preview_category.setText(model.categories);
//            preview_tanggal.setText(model.published_at);
//            tv_content_preview.setText(model.content);
//
//
//        }
//    }
//}
