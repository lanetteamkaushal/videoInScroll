package com.volokh.danylo.video_player_manager.playermessages;

import android.net.Uri;

import com.volokh.danylo.video_player_manager.manager.VideoPlayerManagerCallback;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

/**
 * Created by lcom75 on 5/1/17.
 */

public class setUriDataSourceMessage extends SetDataSourceMessage {

    private final Uri mVideoUrl;

    public setUriDataSourceMessage(VideoPlayerView videoPlayerView, Uri videoUrl, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
        mVideoUrl = videoUrl;
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.setDataSource(mVideoUrl);
    }
}
