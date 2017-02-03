package com.zhuguohui.videodemo.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuguohui.videodemo.R;
import com.zhuguohui.videodemo.activity.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    public NewsListAdapter(String title) {
        this.title = title;
        createdData();
    }

    private void createdData() {

        for (int i = 1; i <= 20; i++) {
            data.add(title + "新闻" + i);
        }
    }

    private List<String> data = new ArrayList<>();


    private String title;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), NewsDetailActivity.class);
            intent.putExtra(NewsDetailActivity.KEY_TITLE, data.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
