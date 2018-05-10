package com.gaurav.githubusers.mvp;

import android.util.Log;
import android.widget.Toast;

import com.gaurav.githubusers.BaseRepo.BaseRepository;
import com.gaurav.githubusers.MainActivity;
import com.gaurav.githubusers.api.Api;
import com.gaurav.githubusers.api.ApiClient;
import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitUserRepositoryImpl implements GitUserContract.GitUserRepository {

    Api api = ApiClient.getClient().create(Api.class);

    @Inject
    MainActivity view;

    @Inject
    GitUserRepositoryImpl() {
    }

    @Override
    public void getAllUsersInteractor() {
        Call<List<UserResponse>> call = api.getUserData();
        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                try {
                    List<UserResponse> result = response.body();
                    if (result.size() > 0)
                        view.updateList(result);
                } catch (NullPointerException e) {
                    Toast.makeText(view, "Data not available", Toast.LENGTH_SHORT).show();
                }
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
                try {
                    List<UserResponse> result = response.body().getItems();
                    if (result.size() > 0)
                        view.updateList(response.body().getItems());
                } catch (NullPointerException e) {
                    Toast.makeText(view, "Data not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    public interface ResponseCallback {
        void updateUI(List<UserResponse> output);
    }
}
