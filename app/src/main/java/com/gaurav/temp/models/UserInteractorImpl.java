package com.gaurav.githubusers.mvp.models;

import com.gaurav.githubusers.api.Api;
import com.gaurav.githubusers.api.ApiClient;
import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;

public class UserInteractorImpl implements UserInteractor {

    List<UserResponse> result;
    Api api = ApiClient.getClient().create(Api.class);

    @Override
    public Call<List<UserResponse>> getAllUsersInteractor() {
        return api.getUserData();
    }

    @Override
    public Call<SearchResponse> searchUser(String userInput) {
        String type = "application/vnd.github.mercy-preview+json";
        return api.searchUser(type, userInput);
    }
}
