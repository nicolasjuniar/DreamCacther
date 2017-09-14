package com.cheteam.dreamcatcher.ArticlePreview.View;

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

import com.cheteam.dreamcatcher.ArticlePreview.API.ViewArticleApi;
import com.cheteam.dreamcatcher.ArticlePreview.Controller.ArticleController;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
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
 * Layout untuk EDIT POST
 */

public class ActivityPreview extends AppCompatActivity implements ArticleController.onViewArticleResponse{
    @BindView(R.id.tv_title_preview) TextView tv_title_preview;
    @BindView(R.id.tv_name_preview) TextView tv_name_preview;
   //@BindView(R.id.preview_category) TextView preview_category;
    @BindView(R.id.preview_tanggal) TextView preview_tanggal;
    @BindView(R.id.tv_content_preview) TextView tv_content_preview;
    //@BindView(R.id.tv_category_preview) TextView tv_category_preview;
    @BindView(R.id.preview_avatar_user) CircleImageView preview_avatar_user;
    @BindView(R.id.bg_preview) ImageView bg_preview;
    @BindView(R.id.bookmark_icon) ImageView bookmark_icon;
    @BindView(R.id.my_toolbar_article_preview) Toolbar myToolbar;
    Boolean cek=false;
    ArticleController AC;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_preview);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Edit");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        AC=new ArticleController(this);
        AC.GetArticle(1);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_article_preview, menu);

        return true;
    }


    @Override
    public void getViewArticleResponse(boolean error, ViewArticleResponse response, Throwable t) {
        if(!error)
        {
            ViewArticleResponse model=response;
            tv_title_preview.setText(model.post_title);
            tv_name_preview.setText(model.name);
            //preview_category.setText("in");
            //tv_category_preview.setText(model.categories);
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
    }
}
