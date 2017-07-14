package com.daystudy.daystudy.pulltorefresh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: hzlishang
 * Data: 16/9/7 下午7:24
 * Des:
 * version:
 */
public class LoadMoreFooterViewHolder extends RecyclerView.ViewHolder implements ILoadMore {
    public LoadMoreFooterViewHolder(View itemView) {
        super(itemView);
        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void startLoadMore() {
        itemView.setVisibility(View.VISIBLE);
    }

    @Override
    public void completeLoadMore() {
        itemView.setVisibility(View.INVISIBLE);
    }
}
