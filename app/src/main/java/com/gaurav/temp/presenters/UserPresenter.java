package com.gaurav.githubusers.mvp.presenters;

import com.gaurav.githubusers.mvp.models.UserInteractor;
import com.gaurav.githubusers.mvp.views.UserView;
import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    UserInteractor userInteractor;
    UserView view;
    List<UserResponse> result;

    public UserPresenter(UserInteractor userInteractor){
        this.userInteractor = userInteractor;
    }

    public void bind(UserView userView) {
        this.view = userView;
        userInteractor.getAllUsersInteractor().enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                view.updateList(response.body());
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }
        });
    }

    public void unbind() {
        this.view = null;
    }

    public void setData() {
        this.view.updateList(result);
    }

    public void performSearch(String inputData) {
        this.userInteractor.searchUser(inputData).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                view.updateList(response.body().getItems());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }
}
