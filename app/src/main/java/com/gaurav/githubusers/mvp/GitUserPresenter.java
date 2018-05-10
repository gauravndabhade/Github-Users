package com.gaurav.githubusers.mvp;

import android.app.Activity;
import android.content.Context;

import com.gaurav.githubusers.BaseRepo.BaseRepository;
import com.gaurav.githubusers.MainActivity;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

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
