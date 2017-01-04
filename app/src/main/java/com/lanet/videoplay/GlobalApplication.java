package com.lanet.videoplay;

import android.app.Application;

import im.ene.toro.Toro;

/**
 * Created by lcom75 on 3/1/17.
 */

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toro.init(this);
    }
}
