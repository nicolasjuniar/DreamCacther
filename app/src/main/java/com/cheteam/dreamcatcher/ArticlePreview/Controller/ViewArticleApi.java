package com.cheteam.dreamcatcher.ArticlePreview.Controller;

import com.cheteam.dreamcatcher.ArticlePreview.Model.ArticleModel;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Rahmat Al Hakam on 09/09/2017.
 */

public interface ViewArticleApi {
    @GET("v1/posts/{id_post}")
    Call<ArticleModel> getArticle(@Path("id_post") int id_post);
}
