package com.lanet.videoplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {

        }
    });
    protected RecyclerView.Adapter adapter;
    RecyclerView rvVideoList;
    private ListItemsVisibilityCalculator mListItemVisibilityCalculator;
    private int mScrollState;
    private RecyclerViewItemPositionGetter mItemsPositionGetter;
    private ArrayList<Item> mList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvVideoList = (RecyclerView) findViewById(R.id.rvVideoList);
        mLayoutManager = getLayoutManager();
        rvVideoList.setLayoutManager(mLayoutManager);
        if (mLayoutManager != null) {
            rvVideoList.addItemDecoration(new DividerItemDecoration(this,
                    mLayoutManager.getOrientation()));
        }
        mList = new ArrayList<>();
        mList.add(new Item("Title 1", "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", mVideoPlayerManager, Picasso.with(this), R.mipmap.ic_launcher));
        mList.add(new Item("Title 2", "http://techslides.com/demos/sample-videos/small.mp4", mVideoPlayerManager, Picasso.with(this), R.mipmap.ic_launcher));
        mList.add(new Item("Title 3", "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", mVideoPlayerManager, Picasso.with(this), R.mipmap.ic_launcher));
        mList.add(new Item("Title 4", "http://techslides.com/demos/sample-videos/small.mp4", mVideoPlayerManager, Picasso.with(this), R.mipmap.ic_launcher));
        adapter = getAdapter();
        rvVideoList.setHasFixedSize(false);
        rvVideoList.setAdapter(adapter);
        mListItemVisibilityCalculator =
                new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mList);
        rvVideoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                mScrollState = scrollState;
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE && !mList.isEmpty()) {
                    try {
                        mListItemVisibilityCalculator.onScrollStateIdle(
                                mItemsPositionGetter,
                                mLayoutManager.findFirstVisibleItemPosition(),
                                mLayoutManager.findLastVisibleItemPosition());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!mList.isEmpty()) {
                    try {
                        mListItemVisibilityCalculator.onScroll(
                                mItemsPositionGetter,
                                mLayoutManager.findFirstVisibleItemPosition(),
                                mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition() + 1,
                                mScrollState);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mItemsPositionGetter = new RecyclerViewItemPositionGetter(mLayoutManager, rvVideoList);
    }

    LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
    }

    RecyclerView.Adapter getAdapter() {
        return new Basic1Adapter(mVideoPlayerManager, MainActivity.this, mList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
//        Toro.unregister(rvVideoList);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toro.register(rvVideoList);
    }
}
