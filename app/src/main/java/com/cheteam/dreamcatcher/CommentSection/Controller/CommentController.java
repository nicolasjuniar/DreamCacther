package com.cheteam.dreamcatcher.CommentSection.Controller;

import com.cheteam.dreamcatcher.CommentSection.API.CommentApi;
import com.cheteam.dreamcatcher.CommentSection.Model.CommentModel;
import com.cheteam.dreamcatcher.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rahmat Al Hakam on 18/09/2017.
 */

public class CommentController {
    CommentApi service;
    Call<CommentModel> CallResponse;
    CommentController.OnCommentResponse listener;

    public CommentController(CommentController.OnCommentResponse listener) {
        this.listener = listener;
    }

    public void Comment(int id_post, int id_comment) {
        service = ServiceGenerator.createService(CommentApi.class);
        CallResponse = service.getComment(id_post, id_comment);
        CallResponse.enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                listener.getCommentResponse(false, response.body(), null);
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable throwable) {
                listener.getCommentResponse(true, null, throwable);
            }
        });
    }

    public interface OnCommentResponse {
        void getCommentResponse(boolean error, CommentModel commentModel, Throwable t);
    }
}
