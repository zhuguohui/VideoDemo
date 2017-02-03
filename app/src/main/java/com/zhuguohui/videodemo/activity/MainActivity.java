package com.zhuguohui.videodemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuguohui.videodemo.R;
import com.zhuguohui.videodemo.adapter.NewsTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String> titleList = new ArrayList<>();

    {
        titleList.add("头条");
        titleList.add("视频");
        titleList.add("体育");
        titleList.add("军事");
        titleList.add("经济");
        titleList.add("娱乐");
        titleList.add("科技");
        titleList.add("汽车");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager.setAdapter(new NewsTabAdapter(getSupportFragmentManager(), titleList));
        tabLayout.setupWithViewPager(viewPager);
    }
}
