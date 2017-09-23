package com.cheteam.dreamcatcher.AddPost.View.Controller;

import com.cheteam.dreamcatcher.AddPost.View.API.AddPostApi;
import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostRequest;
import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostResponse;
import com.cheteam.dreamcatcher.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rahmat Al Hakam on 23/09/2017.
 */

public class AddPostController {
    AddPostApi service;
    Call<AddPostRequest> CallAddPost;

    public void addPost(AddPostRequest post){
        service = ServiceGenerator.createService(AddPostApi.class);
        CallAddPost = service.postArticle(post);
        CallAddPost.enqueue(new Callback<AddPostRequest>() {
            @Override
            public void onResponse(Call<AddPostRequest> call, Response<AddPostRequest> response) {

            }

            @Override
            public void onFailure(Call<AddPostRequest> call, Throwable throwable) {

            }
        });
    }
}
