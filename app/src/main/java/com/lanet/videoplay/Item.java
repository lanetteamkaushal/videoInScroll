package com.lanet.videoplay;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.VideoItem;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.CurrentItemMetaData;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;
import com.volokh.danylo.video_player_manager.utils.Logger;
import com.volokh.danylo.visibility_utils.items.ListItem;

/**
 * Created by lcom75 on 4/1/17.
 */

public class Item implements ListItem, VideoItem {


    private static final boolean SHOW_LOGS = false;
    private static final String TAG = Item.class.getSimpleName();
    private final String mDirectUrl;
    private final String mTitle;
    private final Picasso mImageLoader;
    private final int mImageResource;
    /**
     * An object that is filled with values when {@link #getVisibilityPercents} method is called.
     * This object is local visible rect filled by {@link android.view.View#getLocalVisibleRect}
     */

    private final Rect mCurrentViewRect = new Rect();
    private final VideoPlayerManager<MetaData> mVideoPlayerManager;

    public Item(String title, String directUr, VideoPlayerManager videoPlayerManager, Picasso imageLoader, int imageResource) {
        mVideoPlayerManager = videoPlayerManager;
        mDirectUrl = directUr;
        mTitle = title;
        mImageLoader = imageLoader;
        mImageResource = imageResource;

    }

    public void update(int position, VideoViewHolder viewHolder, VideoPlayerManager videoPlayerManager) {
        viewHolder.mTitle.setText(mTitle);
        viewHolder.mCover.setVisibility(View.VISIBLE);
        mImageLoader.load(mImageResource).into(viewHolder.mCover);
    }

    public View createView(ViewGroup parent, int screenWidth) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = screenWidth;

        final VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        view.setTag(videoViewHolder);

        videoViewHolder.mPlayer.addMediaPlayerListener(new MediaPlayerWrapper.MainThreadMediaPlayerListener() {
            @Override
            public void onVideoSizeChangedMainThread(int width, int height) {
            }

            @Override
            public void onVideoPreparedMainThread() {
                // When video is prepared it's about to start playback. So we hide the cover
                videoViewHolder.mCover.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {
            }

            @Override
            public void onErrorMainThread(int what, int extra) {
            }

            @Override
            public void onBufferingUpdateMainThread(int percent) {
            }

            @Override
            public void onVideoStoppedMainThread() {
                // Show the cover when video stopped
                videoViewHolder.mCover.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInfoMainThread(int what) {

            }
        });
        return view;
    }

    /**
     * This method calculates visibility percentage of currentView. This method works correctly when
     * currentView is smaller then it's enclosure.
     *
     * @param currentView - view which visibility should be calculated
     * @return currentView visibility percents
     */
    @Override
    public int getVisibilityPercents(View currentView) {
        if (SHOW_LOGS) Logger.v(TAG, ">> getVisibilityPercents currentView " + currentView);

        int percents = 100;

        currentView.getLocalVisibleRect(mCurrentViewRect);
        if (SHOW_LOGS)
            Logger.v(TAG, "getVisibilityPercents mCurrentViewRect top " + mCurrentViewRect.top + ", left " + mCurrentViewRect.left + ", bottom " + mCurrentViewRect.bottom + ", right " + mCurrentViewRect.right);

        int height = currentView.getHeight();
        if (SHOW_LOGS) Logger.v(TAG, "getVisibilityPercents height " + height);

        if (viewIsPartiallyHiddenTop()) {
            // view is partially hidden behind the top edge
            percents = (height - mCurrentViewRect.top) * 100 / height;
        } else if (viewIsPartiallyHiddenBottom(height)) {
            percents = mCurrentViewRect.bottom * 100 / height;
        }

        setVisibilityPercentsText(currentView, percents);
        if (SHOW_LOGS) Logger.v(TAG, "<< getVisibilityPercents, percents " + percents);

        return percents;
    }

    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {
        VideoViewHolder viewHolder = (VideoViewHolder) newActiveView.getTag();
        playNewVideo(new CurrentItemMetaData(newActiveViewPosition, newActiveView), viewHolder.mPlayer, mVideoPlayerManager);
    }

    @Override
    public void playNewVideo(MetaData currentItemMetaData, VideoPlayerView player, VideoPlayerManager<MetaData> videoPlayerManager) {
        videoPlayerManager.playNewVideo(currentItemMetaData, player, mDirectUrl);
    }

    @Override
    public void stopPlayback(VideoPlayerManager videoPlayerManager) {
        videoPlayerManager.stopAnyPlayback();
    }

    /**
     * When this item becomes inactive we stop playback on the video in this item.
     */
    @Override
    public void deactivate(View currentView, int position) {
        stopPlayback(mVideoPlayerManager);
    }

    private boolean viewIsPartiallyHiddenTop() {
        return mCurrentViewRect.top > 0;
    }

    private boolean viewIsPartiallyHiddenBottom(int height) {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
    }

    private void setVisibilityPercentsText(View currentView, int percents) {
        if (SHOW_LOGS) Logger.v(TAG, "setVisibilityPercentsText percents " + percents);
        VideoViewHolder videoViewHolder = (VideoViewHolder) currentView.getTag();
        String percentsText = "Visibility percents: " + String.valueOf(percents);

        videoViewHolder.mVisibilityPercents.setText(percentsText);
    }
}
