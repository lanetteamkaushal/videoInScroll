package com.lanet.videoplay;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import im.ene.toro.ToroAdapter;

/**
 * Created by eneim on 6/29/16.
 *
 * Simple ViewHolder which holds no Video. Or it doesn't request support from Toro.
 */
public class Basic1NormalViewHolder extends ToroAdapter.ViewHolder {

    public static final int LAYOUT_RES = R.layout.vh_normal_view;

    public Basic1NormalViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RecyclerView.Adapter adapter, Object item) {

    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onDetachedFromWindow() {

    }
}
