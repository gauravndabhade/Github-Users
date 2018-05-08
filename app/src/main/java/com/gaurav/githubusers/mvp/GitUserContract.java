package com.gaurav.githubusers.mvp;

import com.gaurav.githubusers.response.SearchResponse;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import retrofit2.Call;

public interface GitUserContract {
    interface GitUserAction {

        void onClickSearch(String inpute);
    }

    interface GitUserView {
        void updateList(List<UserResponse> data);

    }

    interface GitUserRepository {
        void getAllUsersInteractor();

        void searchUser(String userInput);

    }
}
