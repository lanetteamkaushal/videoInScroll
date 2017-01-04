package com.volokh.danylo.video_player_manager.playermessages;

import android.media.MediaPlayer;

import com.volokh.danylo.video_player_manager.PlayerMessageState;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManagerCallback;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;


/**
 * This PlayerMessage calls {@link MediaPlayer#pause()} ()} on the instance that is used inside {@link VideoPlayerView}
 */
public class Pause extends PlayerMessage {
    public Pause(VideoPlayerView videoView, VideoPlayerManagerCallback callback) {
        super(videoView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.pause();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.PAUSING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.PAUSED;
    }
}
