package com.zhuguohui.videodemo.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

import com.zhuguohui.videodemo.rx.RxBus;
import com.zhuguohui.videodemo.util.ToastUtil;


/**
 * 监听网络状态改变的服务
 * Created by zhuguohui on 2017/1/18.
 */

public class NetworkStateService extends Service {

    // Class that answers queries about the state of network connectivity.
    // 系统网络连接相关的操作管理类.

    private ConnectivityManager connectivityManager;
    // Describes the status of a network interface.
    // 网络状态信息的实例
    private NetworkInfo info;

    /**
     * 当前处于的网络
     * 0 ：null
     * 1 ：2G/3G
     * 2 ：wifi
     */
    public static int networkStatus;


    /**
     * 广播实例
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction(); //当前接受到的广播的标识(行动/意图)

            // 当当前接受到的广播的标识(意图)为网络状态的标识时做相应判断
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                // 获取网络连接管理器
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                // 获取当前网络状态信息
                info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isAvailable()) {

                    //当NetworkInfo不为空且是可用的情况下，获取当前网络的Type状态
                    //根据NetworkInfo.getTypeName()判断当前网络
                    String name = info.getTypeName();

                    //更改NetworkStateService的静态变量，之后只要在Activity中进行判断就好了
                    if (name.equals("WIFI")) {
                        networkStatus = 2;
                        RxBus.getDefault().post(new NetStateChangeEvent(NetStateChangeEvent.NetState.NET_WIFI));
                    } else {
                        networkStatus = 1;
                        RxBus.getDefault().post(new NetStateChangeEvent(NetStateChangeEvent.NetState.NET_4G));
                    }

                } else {

                    // NetworkInfo为空或者是不可用的情况下
                    networkStatus = 0;

                    RxBus.getDefault().post(new NetStateChangeEvent(NetStateChangeEvent.NetState.NET_NO));
                    // Toast.makeText(context, "没有可用网络!\n请连接网络后刷新本界面", Toast.LENGTH_SHORT).show();
                    ToastUtil.getInstance().showToast("网络不可用");

                }
            }
        }
    };

    public static class NetStateChangeEvent {
      public enum NetState {
            NET_NO, NET_WIFI, NET_4G
        }

        NetState state;

        public NetStateChangeEvent(NetState state) {
            this.state = state;
        }

        public NetState getState() {
            return state;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册网络状态的广播，绑定到mReceiver
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销接收
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        // 获取网络连接管理器
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // 获取当前网络状态信息
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }
}
