package com.gaurav.githubusers.mvp.models;

import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;

public interface UserInteractor {
    public Call<List<UserResponse>> getAllUsersInteractor();

    Call<SearchResponse> searchUser(String userInput);
}
