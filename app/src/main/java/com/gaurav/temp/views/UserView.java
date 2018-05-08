package com.gaurav.githubusers.mvp.views;

import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

public interface UserView {
    void updateList(List<UserResponse> data);
}
