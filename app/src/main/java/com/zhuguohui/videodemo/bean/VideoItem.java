package com.zhuguohui.videodemo.bean;

import java.io.Serializable;

/**
 * Created by zhuguohui on 2017/2/3.
 */

public class VideoItem  implements Serializable{
    /**
     * title : 西藏民俗年味浓 古老歌舞首秀新年舞台
     * imgUrl : http://www.xzcmvideo.cn//masvod/public/2017/01/28/11299.images/v11299_b1485583569795_app.jpg
     * videoUrl : http://www.xzcmvideo.cn//masvod/public/2017/01/28/20170128_159e3ae0361_r1_300k.mp4
     * docUrl : http://news.hexun.com/2017-01-27/187953027.html
     */

    private String title;
    private String imgUrl;
    private String videoUrl;
    private String docUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }

        if(!(obj instanceof VideoItem)){
            return false;
        }

        VideoItem other= (VideoItem) obj;
        return other.getTitle().equals(getTitle())&&other.getDocUrl().equals(getDocUrl())&&other.getVideoUrl().equals(getVideoUrl());
    }
}
