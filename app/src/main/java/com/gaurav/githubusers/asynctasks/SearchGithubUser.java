package com.gaurav.githubusers.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.SearchView;

import com.gaurav.githubusers.MainActivity;
import com.gaurav.githubusers.api.Api;
import com.gaurav.githubusers.api.ApiClient;
import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchGithubUser extends AsyncTask<Void,Void,Void> {

    Api api = ApiClient.getClient().create(Api.class);
    List<UserResponse> users;
    SearchCallback searchCallback;
    String mQuery;

    public SearchGithubUser(MainActivity mainActivity , String query) {
        this.searchCallback = (SearchCallback) mainActivity;
        this.mQuery = query;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String type = "application/vnd.github.mercy-preview+json";
        Call<SearchResponse> call = api.searchUser(type, mQuery);
        Log.d("Called url is:", call.request().url().toString());

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                users = response.body().getItems();
                searchCallback.searchFinish(users);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                t.printStackTrace();
            }
        });
        return null;
    }

    public interface SearchCallback {
        void searchFinish(List<UserResponse> output);

    }
}
