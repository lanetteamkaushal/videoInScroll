package com.volokh.danylo.video_player_manager.manager;


import com.volokh.danylo.video_player_manager.meta.MetaData;

public interface PlayerItemChangeListener {
    void onPlayerItemChanged(MetaData currentItemMetaData);
}
