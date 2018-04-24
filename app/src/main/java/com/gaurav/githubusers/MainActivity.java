package com.gaurav.githubusers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.gaurav.githubusers.adapters.UserAdapter;
import com.gaurav.githubusers.asynctasks.GetGithubUserData;
import com.gaurav.githubusers.asynctasks.SearchGithubUser;
import com.gaurav.githubusers.pagination.EndlessRecyclerViewScrollListener;
import com.gaurav.githubusers.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements GetGithubUserData.ResponseCallback, SearchGithubUser.SearchCallback {

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.users_list) RecyclerView reviewRecyclerView;
    @BindView(R.id.search_text) EditText searchText;
    @BindView(R.id.search_button) Button searchBtn;

    private int offset;
    private boolean isLoading = false;
    final int LOAD_USERS = 3;

    UserAdapter userAdapter;
    List<UserResponse> data = new ArrayList<>();
    List<UserResponse> data2 = new ArrayList<>();
    Context context = MainActivity.this;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        reviewRecyclerView.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.VISIBLE);

        new GetGithubUserData(this).execute();
    }

    @OnClick(R.id.search_button)
    void searchUser() {
        String query = searchText.getText().toString();

        if(query.equals(""))
            if(null == data)
                new GetGithubUserData(MainActivity.this).execute();
            else
                setEndlessAdapter(data);
        else
            new SearchGithubUser(MainActivity.this,query).execute();
    }

    void setEndlessAdapter(final List<UserResponse> dataForPagination) {
        progressBar.setVisibility(View.VISIBLE);
        userAdapter = new UserAdapter(MainActivity.this, R.layout.list_user_layout, dataForPagination);
        reviewRecyclerView.setAdapter(userAdapter);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                progressBar.setVisibility(View.VISIBLE);

                final int curSize = userAdapter.getItemCount();
                if((data.size() - curSize ) >= LOAD_USERS)
                    offset = LOAD_USERS;
                else
                    offset = (data.size() - curSize );

                if (!isLoading) {
                    isLoading = true;
                    final List<UserResponse> itemsLocal = loadMoreReviewData(curSize,offset);
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            dataForPagination.addAll(itemsLocal);
                            userAdapter.notifyItemRangeInserted(curSize, itemsLocal.size() - 1);
                            userAdapter.notifyDataSetChanged();
                            isLoading = false;
                        }
                    });
                }
                progressBar.setVisibility(View.GONE);
            }
        };
        reviewRecyclerView.addOnScrollListener(scrollListener);
        progressBar.setVisibility(View.GONE);
    }

    List<UserResponse> loadMoreReviewData(int curSize,int offset) {
        try {
            return data.subList(curSize, curSize + offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void processFinish(List<UserResponse> output) {
        data = output;
        setEndlessAdapter(data);
    }

    @Override
    public void searchFinish(List<UserResponse> output) {
        data2 = output;
        setEndlessAdapter(data2);
    }
}
