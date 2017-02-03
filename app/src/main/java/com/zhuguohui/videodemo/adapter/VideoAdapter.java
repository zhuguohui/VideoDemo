package com.zhuguohui.videodemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuguohui.videodemo.R;
import com.zhuguohui.videodemo.activity.VideoDetailActivity;
import com.zhuguohui.videodemo.bean.VideoItem;
import com.zhuguohui.videodemo.bean.VideoPage;
import com.zhuguohui.videodemo.rx.RxBus;
import com.zhuguohui.videodemo.video.VideoPlayManager;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    VideoPage videoPage;

    public VideoAdapter(VideoPage videoPage) {
        this.videoPage = videoPage;
    }


    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        final VideoItem videoItem = videoPage.getData().get(position);
        holder.tv_title.setText(videoItem.getTitle());
        Glide.with(holder.itemView.getContext()).load(videoItem.getImgUrl()).into(holder.iv_video);
        holder.layout_holder.setTag(videoItem);
        holder.layout_holder.setOnClickListener(v -> {
            //如果当前视频没有播放，则播放
            RxBus.getDefault().post(new VideoPlayManager.PlayInViewEvent(holder.layout_holder, videoItem, true));
        });
        holder.itemView.setOnClickListener(v -> videoClickListener.onVideoClick(holder.itemView.getContext(), videoItem));
    }

    VideoClickListener videoClickListener = new VideoClickListener();

    public static class VideoClickListener {
        public void onVideoClick(Context context, VideoItem item) {
            Intent intent = new Intent(context, VideoDetailActivity.class);
            intent.putExtra(VideoDetailActivity.KEY_PLAY_ITEM, item);
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return videoPage.getData().size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView iv_video;
        FrameLayout layout_holder;

        public VideoViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_news_title);
            iv_video = (ImageView) itemView.findViewById(R.id.iv_video);
            layout_holder = (FrameLayout) itemView.findViewById(R.id.layout_video_holder);
        }
    }
}
