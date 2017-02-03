package com.zhuguohui.videodemo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuguohui.videodemo.fragment.NewsListFragment;
import com.zhuguohui.videodemo.fragment.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class NewsTabAdapter extends FragmentPagerAdapter {

    private List<String> titleList = new ArrayList<>();

    public NewsTabAdapter(FragmentManager fm, List<String> titleList) {
        super(fm);
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        String title = titleList.get(position);
        if ("视频".equals(title)) {
            return new VideoListFragment();
        }
        Fragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NewsListFragment.KEY_TITLE, titleList.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
