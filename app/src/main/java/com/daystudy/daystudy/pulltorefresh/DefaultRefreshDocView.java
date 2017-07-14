package com.daystudy.daystudy.pulltorefresh;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Author: hzlishang
 * Data: 16/9/7 下午4:03
 * Des:
 * version:
 */
public class DefaultRefreshDocView implements IRefreshDocView {

    private Context mContext;

    public DefaultRefreshDocView(Context context) {
        mContext = context;
    }

    @Override
    public View getRefreshHeaderView() {

        FrameLayout frameLayout = new FrameLayout(mContext);
        TextView textView = new TextView(mContext);
        textView.setText("Pull Down To Refresh");
        textView.setGravity(Gravity.CENTER);
        ProgressBar progressBar = new ProgressBar(mContext);
        frameLayout.addView(progressBar);
        frameLayout.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return frameLayout;
    }

    @Override
    public View getLoadMoreFooterView() {
        FrameLayout frameLayout = new FrameLayout(mContext);
        TextView textView = new TextView(mContext);
        textView.setText("Pull Up To Load More");
        textView.setGravity(Gravity.CENTER);
        ProgressBar progressBar = new ProgressBar(mContext);
        frameLayout.addView(progressBar);
        frameLayout.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return frameLayout;
    }
}
