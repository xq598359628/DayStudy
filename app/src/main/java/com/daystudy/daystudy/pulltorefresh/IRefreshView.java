package com.daystudy.daystudy.pulltorefresh;

/**
 * Author: hzlishang
 * Data: 16/9/7 下午3:55
 * Des:
 * version:
 */
public interface IRefreshView {

    void startRefresh();

    void startLoadMore();

    void completeRefresh();

    void completeLoadMore();

    void enableLoadMore();

    void disableLoadMore();

    void setLoadMoreComplete(boolean hasMore);

    void setHasMore(boolean hasMore);

    void enableRefresh();

    void disableRefresh();

    void setCustomedHeadFooter(IRefreshDocView view);

    boolean hasMore();
}
