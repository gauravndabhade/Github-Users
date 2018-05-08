package com.gaurav.githubusers.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.gaurav.githubusers.MainActivity;
import com.gaurav.githubusers.api.Api;
import com.gaurav.githubusers.api.ApiClient;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetGithubUserData extends AsyncTask<Void,Void,Void> {

    Api api = ApiClient.getClient().create(Api.class);
    List<UserResponse> users;
    public ResponseCallback responseCallback;

    public GetGithubUserData(MainActivity mainActivity) {
        this.responseCallback = (ResponseCallback) mainActivity;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Call<List<UserResponse>> call = api.getUserData();
        Log.d("Called url is:", call.request().url().toString());

        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                users = response.body();
                responseCallback.processFinish(users);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }
        });
        return null;
    }

    public interface ResponseCallback {
        void processFinish(List<UserResponse> output);
    }
}
