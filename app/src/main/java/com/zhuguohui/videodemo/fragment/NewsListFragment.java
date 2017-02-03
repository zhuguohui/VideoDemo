package com.zhuguohui.videodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.zhuguohui.videodemo.adapter.NewsListAdapter;
import com.zhuguohui.videodemo.fragment.base.BaseListFragment;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class NewsListFragment extends BaseListFragment {
    private String title = "";
    public static final String KEY_TITLE = "key_title";
    private NewsListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(KEY_TITLE)) {
            title = getArguments().getString(KEY_TITLE);
        }

    }


    @Override
    protected RecyclerView.Adapter getAdapter() {
        return adapter == null ? adapter = new NewsListAdapter(title) : adapter;
    }
}
