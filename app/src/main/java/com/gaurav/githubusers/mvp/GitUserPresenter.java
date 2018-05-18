package com.gaurav.githubusers.mvp;

import javax.inject.Inject;


public class GitUserPresenter implements GitUserContract.GitUserAction{

    @Inject
    GitUserRepositoryImpl repo;

    @Inject
    public GitUserPresenter() {
    }

    public void setUserList() {
        repo.getAllUsersInteractor();
    }

    @Override
    public void onClickSearch(String input) {
        if(null != input || "".equals(input) )
            repo.searchUser(input);
        else
            repo.getAllUsersInteractor();
    }
}
