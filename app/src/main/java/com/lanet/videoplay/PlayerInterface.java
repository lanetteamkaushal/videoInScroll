package com.lanet.videoplay;


import com.volokh.danylo.video_player_manager.PlayerMessageState;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManagerCallback;
import com.volokh.danylo.video_player_manager.playermessages.PlayerMessage;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

/**
 * Created by lcom75 on 4/1/17.
 */

public class PlayerInterface extends PlayerMessage {

    private final VideoPlayerView currentPlayer;
    private final VideoPlayerManagerCallback callback;

    static enum ACTION {ACTION_STOP, ACTION_RESET, ACTION_RELEASE, ACTION_CLEAR}

    ;
    ACTION performAction;

    public PlayerInterface(VideoPlayerView currentPlayer, VideoPlayerManagerCallback callback, ACTION performAction) {
        super(currentPlayer, callback);
        this.currentPlayer = currentPlayer;
        this.callback = callback;
        this.performAction = performAction;
    }

    public void performAction(ACTION performAction) {
        this.performAction = performAction;
        performAction(currentPlayer);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        if (performAction == ACTION.ACTION_STOP)
            currentPlayer.stop();
        if (performAction == ACTION.ACTION_RESET)
            currentPlayer.reset();
        if (performAction == ACTION.ACTION_RELEASE)
            currentPlayer.release();
        if (performAction == ACTION.ACTION_CLEAR)
            currentPlayer.stop();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.STOPPING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.STOPPED;
    }
}
