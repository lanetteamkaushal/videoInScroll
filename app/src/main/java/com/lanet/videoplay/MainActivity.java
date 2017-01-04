package com.lanet.videoplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.volokh.danylo.video_player_manager.MessagesHandlerThread;
import com.volokh.danylo.video_player_manager.PlayerMessageState;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManagerCallback;
import com.volokh.danylo.video_player_manager.meta.CurrentItemMetaData;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, VideoPlayerManagerCallback {

    private VideoPlayerView mVideoPlayer_1;
    private ImageView mVideoCover;
    private VideoPlayerView mVideoPlayer_2;
    private ImageView mVideoCover2;
    private static final String TAG = "MainActivity";
    private MessagesHandlerThread mPlayerHandler;
    MediaPlayerWrapper mediaPlayerWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoPlayer_1 = (VideoPlayerView) findViewById(R.id.video_player_1);
        mVideoPlayer_1.addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener() {
            @Override
            public void onVideoPreparedMainThread() {
                // We hide the cover when video is prepared. Playback is about to start
                Log.d(TAG, "onVideoPreparedMainThread() called");
                mVideoCover.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onVideoStoppedMainThread() {
                // We show the cover when video is stopped
                Log.d(TAG, "onVideoStoppedMainThread() called");
                mVideoCover.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {
                // We show the cover when video is completed
                Log.d(TAG, "onVideoCompletionMainThread() called");
                mVideoCover.setVisibility(View.VISIBLE);
            }

            @Override
            public void onErrorMainThread(int what, int extra) {
                super.onErrorMainThread(what, extra);
                Log.d(TAG, "onErrorMainThread() called with: what = [" + what + "], extra = [" + extra + "]");
            }

            @Override
            public void onVideoSizeChangedMainThread(int width, int height) {
                super.onVideoSizeChangedMainThread(width, height);
                Log.d(TAG, "onVideoSizeChangedMainThread() called with: width = [" + width + "], height = [" + height + "]");
            }

            @Override
            public void onBufferingUpdateMainThread(int percent) {
                super.onBufferingUpdateMainThread(percent);
                Log.d(TAG, "onBufferingUpdateMainThread() called with: percent = [" + percent + "]");
            }
        });

        mVideoCover = (ImageView) findViewById(R.id.video_cover_1);
        mVideoCover.setOnClickListener(this);

        mVideoPlayer_2 = (VideoPlayerView) findViewById(R.id.video_player_2);
        mVideoPlayer_2.addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener() {
            @Override
            public void onVideoPreparedMainThread() {
                // We hide the cover when video is prepared. Playback is about to start
                mVideoCover2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onVideoStoppedMainThread() {
                // We show the cover when video is stopped
                mVideoCover2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {
                // We show the cover when video is completed
                mVideoCover2.setVisibility(View.VISIBLE);
            }
        });
        mVideoCover2 = (ImageView) findViewById(R.id.video_cover_2);
        mVideoCover2.setOnClickListener(this);
        mPlayerHandler = new MessagesHandlerThread();

        (findViewById(R.id.stopAll)).setOnClickListener(this);
    }

    VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {
            Log.d(TAG, "onPlayerItemChanged() called with: metaData = [" + metaData + "]");
        }
    });

    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_cover_1:
                mVideoPlayerManager.playNewVideo(new CurrentItemMetaData(0, mVideoPlayer_1), mVideoPlayer_1, "http://techslides.com/demos/sample-videos/small.mp4");
                break;
            case R.id.video_cover_2:
                mVideoPlayerManager.playNewVideo(new CurrentItemMetaData(0, mVideoPlayer_2), mVideoPlayer_2, "http://techslides.com/demos/sample-videos/small.mp4");
                break;
            case R.id.stopAll:
                pauseAll();
                break;
        }
    }

    private void pauseAll() {
        try {
            if (mVideoPlayer_1.getMediaPlayer() != null) {
                if (mVideoPlayer_1.getCurrentState() == MediaPlayerWrapper.State.STARTED) {
                    mPlayerHandler.addMessage(new PlayerInterface(mVideoPlayer_1, this, PlayerInterface.ACTION.ACTION_STOP));
                }
            }
            if (mVideoPlayer_2.getMediaPlayer() != null) {
                if (mVideoPlayer_2.getCurrentState() == MediaPlayerWrapper.State.STARTED) {
                    mPlayerHandler.addMessage(new PlayerInterface(mVideoPlayer_2, this, PlayerInterface.ACTION.ACTION_STOP));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCurrentItem(MetaData currentItemMetaData, VideoPlayerView newPlayerView) {
        Log.d(TAG, "setCurrentItem() called with: currentItemMetaData = [" + currentItemMetaData + "], newPlayerView = [" + newPlayerView + "]");
    }

    @Override
    public void setVideoPlayerState(VideoPlayerView videoPlayerView, PlayerMessageState playerMessageState) {
        Log.d(TAG, "setVideoPlayerState() called with: videoPlayerView = [" + videoPlayerView + "], playerMessageState = [" + playerMessageState + "]");
    }

    @Override
    public PlayerMessageState getCurrentPlayerState() {
        return null;
    }
}
