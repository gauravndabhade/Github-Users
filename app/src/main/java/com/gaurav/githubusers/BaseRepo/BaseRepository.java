package com.gaurav.githubusers.BaseRepo;


public class BaseRepository<V> {

    protected V view;

    public void attachView(V view) {
        this.view = view;
    }

    public V getView() {
        return this.view;
    }

}
