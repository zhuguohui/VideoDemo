package com.zhuguohui.videodemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhuguohui.videodemo.R;
import com.zhuguohui.videodemo.bean.VideoItem;
import com.zhuguohui.videodemo.rx.RxBus;
import com.zhuguohui.videodemo.video.VideoPlayManager;

import java.io.Serializable;

public class VideoDetailActivity extends AppCompatActivity {
    FrameLayout video_holder;
    ImageView iv_video;
    private VideoItem videoItem;
    public static String KEY_PLAY_ITEM = "key_play_item";
    private boolean autoPlayVideo = false;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        iv_video = (ImageView) findViewById(R.id.iv_video);
        video_holder = (FrameLayout) findViewById(R.id.video_holder);
        Serializable obj = getIntent().getSerializableExtra(KEY_PLAY_ITEM);
        if (obj != null) {
            videoItem = (VideoItem) obj;
            autoPlayVideo = true;
        } else {
            //保存正在播放的视频信息，因为在这个界面重播的时候，需要这些信息
            videoItem = VideoPlayManager.getPlayingItem();
        }
        //设置缩略图
        Glide.with(this).load(videoItem.getImgUrl()).into(iv_video);
        findViewById(R.id.iv_play).setOnClickListener(v -> {
            RxBus.getDefault().post(new VideoPlayManager.PlayInViewEvent(video_holder, videoItem));
        });
        //设置内容
        webView= (WebView) findViewById(R.id.webView);
        webView.loadUrl(videoItem.getDocUrl());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        //当从全屏返回这个界面的时候，判断是否还有播放的item，防止再次自动播放
        //只有从列表或小窗进入这个界面的时候才自动播放
        if (VideoPlayManager.getPlayingItem() != null || autoPlayVideo) {
            autoPlayVideo = false;
            RxBus.getDefault().post(new VideoPlayManager.PlayInViewEvent(video_holder, videoItem));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBus.getDefault().post(new VideoPlayManager.PlayVideoBackEvent());
    }
}
