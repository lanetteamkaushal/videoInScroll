package com.lanet.videoplay;

/**
 * Created by lcom75 on 3/1/17.
 */

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import im.ene.toro.exoplayer2.ExoVideoView;
import im.ene.toro.exoplayer2.ExoVideoViewHolder;


/**
 * Created by eneim on 6/29/16.
 *
 * This sample use {@link ExoVideoView} API to play medias.
 */
public class Basic1VideoViewHolder extends ExoVideoViewHolder {

    public static final int LAYOUT_RES = R.layout.vh_toro_video_basic;

    private SimpleVideoObject videoItem;

    public Basic1VideoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RecyclerView.Adapter adapter, Object item) {
        if (!(item instanceof SimpleVideoObject)) {
            throw new IllegalArgumentException("Invalid Object: " + item);
        }

        this.videoItem = (SimpleVideoObject) item;
        this.videoView.setMedia(Uri.parse(this.videoItem.video));
    }

    @Override
    protected ExoVideoView findVideoView(View itemView) {
        return (ExoVideoView) itemView.findViewById(R.id.video);
    }

    @Nullable
    @Override
    public String getMediaId() {
        return this.videoItem != null ? this.videoItem.video + "@" + getAdapterPosition() : null;
    }
}