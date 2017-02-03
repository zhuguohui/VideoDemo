package com.zhuguohui.videodemo.video;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.zhuguohui.videodemo.R;


/**
 * Created by zhuguohui on 2016/12/23 0023.
 */

public class ViedoPlayChecker {
    //允许在非wifi环境下播放视频
    private static final boolean ALLOW_PALY_IN_NOT_WIFI_NET = false;
    private static final boolean HAVE_SET = false;

    public interface ActionListener {
        void doAction();
    }

    public static void checkPlayNet(Context context, ActionListener allowListener, ActionListener cancleListener) {
        //如果在wifi网络直接return true
        if (isWifi(context)) {
            if (allowListener != null) {
                allowListener.doAction();
            }
            return;
        }

        FragmentActivity fragmentActivity = null;
        if (!(context instanceof FragmentActivity)) {
            throw new IllegalArgumentException("must use FragmentActivity");
        }
        fragmentActivity = (FragmentActivity) context;

        // Fragment instantiate = NetWarringFragment.instantiate(context, "");
        NetWarringFragment netWarringFragment = new NetWarringFragment(allowListener, cancleListener);
        netWarringFragment.show(fragmentActivity.getFragmentManager(), "");
    }


    public static class NetWarringFragment extends DialogFragment {
        public boolean allow = false;
        public ActionListener actionListener, cancleListener;

        public NetWarringFragment() {
            this(null, null);
        }

        @SuppressLint("ValidFragment")
        public NetWarringFragment(ActionListener allowListener, ActionListener cancleListener) {
            super();
            this.actionListener = allowListener;
            this.cancleListener = cancleListener;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            View view = inflater.inflate(R.layout.fragment_play_net_allow, container, false);
            view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                    if (cancleListener != null) {
                        cancleListener.doAction();
                    }
                }
            });
            view.findViewById(R.id.btn_allow).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                    if (actionListener != null) {
                        actionListener.doAction();
                    }
                }
            });
            return view;
        }
    }

    /**
     * make true current connect service is wifi
     *
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
}
