package com.gaurav.githubusers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gaurav.githubusers.R;
import com.gaurav.githubusers.response.UserResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context mContext;
    private int itemLayoutId;
    private List<UserResponse> data;

    public UserAdapter(Context mContext, int itemLayoutId, List<UserResponse> data) {
        this.mContext = mContext;
        this.itemLayoutId = itemLayoutId;
        this.data = data;
    }

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        return new UserAdapter.UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder,final int position) {

        holder.title.setText(data.get(position).getLogin());
        holder.comment.setText(data.get(position).getUrl());

        Glide.with(mContext).load(data.get(position).getAvatar_url())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"Hello "+data.get(position).getLogin(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        boolean isLoadingAdded = false;
        int LOADING = 0;
        int ITEM = 1;
        return (position == data.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView title;
        @BindView(R.id.comment)TextView comment;
        @BindView(R.id.iv)ImageView iv;
        @BindView(R.id.card_layout)
        CardView cardView;

        private UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
