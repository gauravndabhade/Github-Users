package com.gaurav.githubusers.mvp;

import com.gaurav.githubusers.response.UserResponse;
import java.util.List;

public interface GitUserContract {

    interface GitUserAction {
        void onClickSearch(String input);
    }

    interface GitUserView {
        void updateList(List<UserResponse> data);
    }

    interface GitUserRepository {
        void getAllUsersInteractor();
        void searchUser(String userInput);
    }
}
