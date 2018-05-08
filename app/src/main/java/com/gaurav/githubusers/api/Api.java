package com.gaurav.githubusers.api;

import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface Api {
    @GET("users")
    Call<List<UserResponse>> getUserData();

    @GET("search/users")
    Call<SearchResponse> searchUser(
            @Header("Accept") String type,
            @Query("q") String userName
    );
}
