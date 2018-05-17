package com.gaurav.githubusers.mvp.di;

import android.app.Activity;

import com.gaurav.githubusers.MainActivity;
import com.gaurav.githubusers.api.Api;
import com.gaurav.githubusers.api.ApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public class GitUserModule {

    MainActivity mainActivity;

    public GitUserModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    MainActivity getMainActivity() {
        return mainActivity;
    }

    @Provides
    public Api getApiInstance() {
        return  ApiClient.getClient().create(Api.class);

    }
}
