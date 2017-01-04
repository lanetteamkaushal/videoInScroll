package com.lanet.videoplay;

import android.support.annotation.Nullable;
import android.view.ViewParent;

import java.util.List;

import im.ene.toro.ToroPlayer;
import im.ene.toro.ToroStrategy;

/**
 * Created by lcom75 on 4/1/17.
 */

public class MytoroStrategy implements ToroStrategy {
    @Override
    public String getDescription() {
        return null;
    }

    @Nullable
    @Override
    public ToroPlayer findBestPlayer(List<ToroPlayer> candidates) {
        return null;
    }

    @Override
    public boolean allowsToPlay(ToroPlayer player, ViewParent parent) {
        return false;
    }
}
