package com.zhuguohui.videodemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhuguohui.videodemo.R;

public class NewsDetailActivity extends AppCompatActivity {
    public static final String KEY_TITLE = "key_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        if (getIntent() != null && getIntent().hasExtra(KEY_TITLE)) {
            String title = getIntent().getStringExtra(KEY_TITLE);
            TextView tv_title = (TextView) findViewById(R.id.tv_title);
            tv_title.setText(title);
        }
    }
}
