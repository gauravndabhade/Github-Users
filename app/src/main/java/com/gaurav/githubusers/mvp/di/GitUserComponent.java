package com.gaurav.githubusers.mvp.di;

import com.gaurav.githubusers.MainActivity;

import dagger.Component;

@GitUserScope
@Component(modules = GitUserModule.class)
public interface GitUserComponent {
    void injectGitHub(MainActivity mainActivity);
}
