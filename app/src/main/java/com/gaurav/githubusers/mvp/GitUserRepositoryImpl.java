package com.gaurav.githubusers.mvp;

import android.util.Log;
import android.widget.Toast;

import com.gaurav.githubusers.api.Api;
import com.gaurav.githubusers.api.ApiClient;
import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitUserRepositoryImpl implements GitUserContract.GitUserRepository {

    Api api = ApiClient.getClient().create(Api.class);
    static List<UserResponse> result = null;
    static SearchResponse result2 = null;
    ResponseCallback responseCallback;

    GitUserRepositoryImpl(ResponseCallback callbackResponse) {
        this.responseCallback = callbackResponse;
    }


    @Override
    public void getAllUsersInteractor() {
        Call<List<UserResponse>> call = api.getUserData();
        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                result = response.body();
                responseCallback.processFinish(result);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void searchUser(String userInput) {
        String type = "application/vnd.github.mercy-preview+json";

        Call<SearchResponse> call = api.searchUser(type, userInput);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                responseCallback.processFinish(response.body().getItems());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());

            }
        });
    }

    public interface ResponseCallback {
        void processFinish(List<UserResponse> output);
    }

}