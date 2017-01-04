package com.volokh.danylo.video_player_manager.ui;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerWrapperImpl extends MediaPlayerWrapper{

    /**
     * Can custom MediaPlayer here
     */
    public MediaPlayerWrapperImpl(Context context) {
        super(new MediaPlayer(),context);
    }
}
