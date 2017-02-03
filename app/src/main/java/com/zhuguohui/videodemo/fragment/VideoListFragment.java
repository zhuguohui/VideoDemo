package com.zhuguohui.videodemo.fragment;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.zhuguohui.videodemo.R;
import com.zhuguohui.videodemo.adapter.VideoAdapter;
import com.zhuguohui.videodemo.bean.VideoPage;
import com.zhuguohui.videodemo.fragment.base.BaseListFragment;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class VideoListFragment extends BaseListFragment {

    VideoAdapter adapter;


    @Override
    protected RecyclerView.Adapter getAdapter() {
        if (adapter == null) {
            String jsonStr = readRawFile();
            Gson gson = new Gson();
            VideoPage videoPage = gson.fromJson(jsonStr, VideoPage.class);
            adapter = new VideoAdapter(videoPage);
        }
        return adapter;
    }

    String readRawFile() {
        String content = "";
        Resources resources = getContext().getResources();
        InputStream is = null;
        try {
            is = resources.openRawResource(R.raw.video_list);
            byte buffer[] = new byte[is.available()];
            is.read(buffer);
            content = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
}
