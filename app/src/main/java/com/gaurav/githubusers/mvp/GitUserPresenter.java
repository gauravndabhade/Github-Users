package com.gaurav.githubusers.mvp;

import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

public class GitUserPresenter implements GitUserContract.GitUserAction, GitUserRepositoryImpl.ResponseCallback {

    GitUserContract.GitUserRepository repo;
    GitUserContract.GitUserView view;

    public GitUserPresenter() {
        this.repo = new GitUserRepositoryImpl(this);
    }

    public void bind(GitUserContract.GitUserView view) {
        this.view = view;
    }

    public void unbind() {
        this.view = null;
    }

    public void setUserList() {
        repo.getAllUsersInteractor();
    }

    @Override
    public void onClickSearch(String input) {
        if(null != input)
            repo.searchUser(input);
        else
            repo.getAllUsersInteractor();
    }

    @Override
    public void processFinish(List<UserResponse> output) {
        view.updateList(output);
    }
}
