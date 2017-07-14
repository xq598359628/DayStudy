package com.daystudy.daystudy.day5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.daystudy.daystudy.R;


/**
 * Created by shenxl on 2016/12/5.
 */

public class ListFooterLoadingView {
    private Context context;
    private View mRoot;
    private CustomLoadingView loadingView;

    public ListFooterLoadingView(Context context) {
        this.context = context;
        mRoot = LayoutInflater.from(context).inflate(R.layout.m_community_view_footer_loading, null);
        loadingView = (CustomLoadingView) mRoot.findViewById(R.id.community_home_refresh_image);
    }

    public View getView() {
        return mRoot;
    }

    public void startRefresh() {
        loadingView.startRefresh();
    }

    public void endRefresh() {
        loadingView.endRefresh();
    }
}
