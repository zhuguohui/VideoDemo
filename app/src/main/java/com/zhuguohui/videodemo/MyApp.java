package com.zhuguohui.videodemo;

import android.app.Application;
import android.content.Intent;

import com.zhuguohui.videodemo.service.NetworkStateService;
import com.zhuguohui.videodemo.util.ToastUtil;
import com.zhuguohui.videodemo.video.VideoPlayManager;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ToastUtil.initialize(this);
        //启动网络状态监听服务
        startService(new Intent(this, NetworkStateService.class));
        //初始化视频播放管理器
        VideoPlayManager.init(this);
    }
}
