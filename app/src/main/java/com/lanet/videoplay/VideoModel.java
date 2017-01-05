package com.lanet.videoplay;

import android.net.Uri;

/**
 * Created by lcom75 on 4/1/17.
 */

public class VideoModel {
    public String coverImage;
    public Uri videoUrl;
    public int position;

    public VideoModel(String coverImage, Uri videoUrl) {
        this.coverImage = coverImage;
        this.videoUrl = videoUrl;
    }

    public VideoModel() {

    }
}
