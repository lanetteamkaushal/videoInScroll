package com.lanet.videoplay;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import im.ene.toro.ToroPlayer;

/**
 * Created by lcom75 on 4/1/17.
 */

public class MyPlayerViewHolder extends RecyclerView.ViewHolder implements ToroPlayer {
    public MyPlayerViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public boolean isPrepared() {
        return false;
    }

    @Override
    public boolean wantsToPlay() {
        return false;
    }

    @Override
    public float visibleAreaOffset() {
        return 0;
    }

    @Nullable
    @Override
    public String getMediaId() {
        return null;
    }

    @Override
    public int getPlayOrder() {
        return 0;
    }

    @NonNull
    @Override
    public View getPlayerView() {
        return null;
    }

    @Override
    public void onActivityActive() {

    }

    @Override
    public void onActivityInactive() {

    }

    @Override
    public void onVideoPreparing() {

    }

    @Override
    public void onVideoPrepared() {

    }

    @Override
    public void onPlaybackStarted() {

    }

    @Override
    public void onPlaybackPaused() {

    }

    @Override
    public void onPlaybackCompleted() {

    }

    @Override
    public boolean onPlaybackError(Exception error) {
        return false;
    }

    @Override
    public void preparePlayer(boolean playWhenReady) {

    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void releasePlayer() {

    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public long getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(long pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void setVolume(@FloatRange(from = 0.0, to = 1.0) float volume) {

    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }
}
