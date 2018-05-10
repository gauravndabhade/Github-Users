package com.gaurav.githubusers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gaurav.githubusers.BaseRepo.BaseRepository;
import com.gaurav.githubusers.adapters.UserAdapter;
import com.gaurav.githubusers.mvp.GitUserContract;
import com.gaurav.githubusers.mvp.GitUserPresenter;
import com.gaurav.githubusers.mvp.di.DaggerGitUserComponent;
import com.gaurav.githubusers.mvp.di.GitUserComponent;
import com.gaurav.githubusers.mvp.di.GitUserModule;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements
        GitUserContract.GitUserView{

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.users_list) RecyclerView reviewRecyclerView;
    @BindView(R.id.search_text) EditText searchText;
    @BindView(R.id.search_button) Button searchBtn;

    UserAdapter userAdapter;
    LinearLayoutManager linearLayoutManager;

    @Inject
    GitUserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportActionBar().hide();

        GitUserComponent gitUserComponent = DaggerGitUserComponent.builder()
                .gitUserModule(new GitUserModule(this)).build();

        gitUserComponent.injectGitHub(this);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        reviewRecyclerView.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.VISIBLE);
        userPresenter.setUserList();
    }

    @OnClick(R.id.search_button)
    void searchUser() {

        String query = searchText.getText().toString();
        if(!query.equals("")) {
            userPresenter.onClickSearch(query);
        }
        else {
            userPresenter.setUserList();
            Toast.makeText(this, "Please search something!!", Toast.LENGTH_SHORT).show();
        }
    }

    void setEndlessAdapter(final List<UserResponse> dataForPagination) {
        progressBar.setVisibility(View.VISIBLE);
        userAdapter = new UserAdapter(MainActivity.this, R.layout.list_user_layout, dataForPagination);
        reviewRecyclerView.setAdapter(userAdapter);

        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void updateList(List<UserResponse> data) {
        if(null != data || data.size() > 0)
            setEndlessAdapter(data);
        else
            Log.d("MainActivity","No data");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
