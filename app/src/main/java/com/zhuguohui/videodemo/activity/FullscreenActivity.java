package com.zhuguohui.videodemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.zhuguohui.videodemo.R;
import com.zhuguohui.videodemo.rx.RxBus;
import com.zhuguohui.videodemo.video.VideoPlayManager;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        frameLayout = (FrameLayout) findViewById(R.id.full_holder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxBus.getDefault().post(new VideoPlayManager.PlayInViewEvent(frameLayout, VideoPlayManager.getPlayingItem()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBus.getDefault().post(new VideoPlayManager.PlayVideoBackEvent());
    }
}
