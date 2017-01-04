package com.lanet.videoplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;

import java.util.List;

/**
 * Created by lcom75 on 4/1/17.
 */
public class Basic1Adapter extends RecyclerView.Adapter<VideoViewHolder> {

    private final VideoPlayerManager mVideoPlayerManager;
    private final List<Item> mList;
    private final Context mContext;

    public Basic1Adapter(VideoPlayerManager videoPlayerManager, Context context, List<Item> list) {
        mVideoPlayerManager = videoPlayerManager;
        mContext = context;
        mList = list;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Item videoItem = mList.get(position);
        View resultView = videoItem.createView(viewGroup, mContext.getResources().getDisplayMetrics().widthPixels);
        return new VideoViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder viewHolder, int position) {
        Item videoItem = mList.get(position);
        videoItem.update(position, viewHolder, mVideoPlayerManager);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
