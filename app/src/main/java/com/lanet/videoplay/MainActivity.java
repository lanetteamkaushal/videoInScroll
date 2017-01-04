package com.lanet.videoplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import im.ene.toro.Toro;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvVideoList;
    protected RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toro.attach(this);
        rvVideoList = (RecyclerView) findViewById(R.id.rvVideoList);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        rvVideoList.setLayoutManager(layoutManager);
        if (layoutManager instanceof LinearLayoutManager) {
            rvVideoList.addItemDecoration(new DividerItemDecoration(this,
                    ((LinearLayoutManager) layoutManager).getOrientation()));
        }
        adapter = getAdapter();
        rvVideoList.setHasFixedSize(false);
        rvVideoList.setAdapter(adapter);

    }

    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
    }

    RecyclerView.Adapter getAdapter() {
        return new Basic1Adapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toro.register(rvVideoList);
    }

    @Override
    protected void onPause() {
        Toro.unregister(rvVideoList);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Toro.detach(this);
        super.onDestroy();
    }
}
