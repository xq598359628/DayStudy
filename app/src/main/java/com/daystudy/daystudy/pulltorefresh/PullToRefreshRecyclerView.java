package com.daystudy.daystudy.pulltorefresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Author: hzlishang
 * Data: 16/9/7 下午3:56
 * Des:
 * version:
 */
public class PullToRefreshRecyclerView extends LinearLayout implements IRefreshView {

    private static final int MAX_SCROLL_DISTANCE = 400;

    private float mDownY;
    private int mTouchSlop;
    private int mRefreshHeight;
    private int mLastPadding;
    private boolean mCanLoadMore;
    private boolean mCanRefresh;
    private boolean mHasMore;

    private IRefreshListener mIRefreshListener;
    private LinearLayout mLoadMoreFooterView;
    private LinearLayout mRefreshHeaderView;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterWraper mRecyclerAdapterWraper;
    private ValueAnimator mValueAnimator;
    private State mCurrentState = State.NORMAL;


    public PullToRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setIRefreshListener(IRefreshListener IRefreshListener) {
        mIRefreshListener = IRefreshListener;
    }

    private void initView() {
        setOrientation(VERTICAL);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mRecyclerView = new RecyclerView(getContext());
        mLoadMoreFooterView = new LinearLayout(getContext());
        mRefreshHeaderView = new LinearLayout(getContext());
        setCustomedHeadFooter(new DefaultRefreshDocView(getContext()));
        addView(mRefreshHeaderView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mHasMore && newState == RecyclerView.SCROLL_STATE_IDLE && shouldLoadMore()) {
                    startLoadMore();
                }
            }
        });
        enableLoadMore();
    }

    private boolean shouldLoadMore() {
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            int lastVisibleItemPosition = linearManager.findLastVisibleItemPosition();
            return lastVisibleItemPosition+1 >= layoutManager.getItemCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager stagerManager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPos = stagerManager.findLastVisibleItemPositions(null);
            int tmp = lastPos[0];
            for (int i = 0; i < lastPos.length - 1; i++) {
                if (lastPos[i] < lastPos[i + 1])
                    tmp = lastPos[i + 1];
            }
            return tmp + 1 >= layoutManager.getItemCount();
        }
        return false;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                cancleAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getY() - mDownY > mTouchSlop && !ViewCompat.canScrollVertically(mRecyclerView, (int) (mDownY - ev.getY()))) {
                    mDownY = ev.getY();
                    mLastPadding = mRefreshHeaderView.getPaddingTop();
                    mCurrentState = State.DRAGGING;
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    private void cancleAnimation() {
        if (mValueAnimator != null)
            mValueAnimator.cancel();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (!mCanRefresh) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                if (ev.getY() - mDownY < 2 * MAX_SCROLL_DISTANCE)
                    mRefreshHeaderView.setPadding(0, (int) (mLastPadding + (ev.getY() - mDownY) / 2), 0, 0);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mRefreshHeaderView.getPaddingTop() > 0) {
                    startRefresh();
                    mCurrentState = State.REFRESHING;
                    flingToTop(mRefreshHeaderView.getPaddingTop(), 0);
                } else {
                    flingToTop(mRefreshHeaderView.getPaddingTop(), -mRefreshHeight);
                }
                break;
        }
        return true;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mCanLoadMore) {
            mRecyclerAdapterWraper = new RecyclerAdapterWraper(adapter, mLoadMoreFooterView);
            mRecyclerView.setAdapter(mRecyclerAdapterWraper);
        } else {
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
            mRecyclerView.setLayoutManager(manager);
    }

    private void flingToTop(int from, int to) {
        mValueAnimator = ValueAnimator.ofInt(from, to);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatorValue = (int) valueAnimator.getAnimatedValue();
                mRefreshHeaderView.setPadding(0, animatorValue, 0, 0);
            }
        });
        mValueAnimator.start();
    }

    @Override
    public void setCustomedHeadFooter(IRefreshDocView view) {
        mRefreshHeaderView.removeAllViews();
        mRefreshHeaderView.setPadding(0, 0, 0, 0);
        mRefreshHeaderView.addView(view.getRefreshHeaderView(),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int measureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        mRefreshHeaderView.measure(measureSpec, measureSpec);
        mRefreshHeight = mRefreshHeaderView.getMeasuredHeight();
        mRefreshHeaderView.setPadding(0, -mRefreshHeight, 0, 0);
        mLoadMoreFooterView.removeAllViews();
        mLoadMoreFooterView.addView(view.getLoadMoreFooterView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void enableLoadMore() {
        mCanLoadMore = true;
    }

    @Override
    public void disableLoadMore() {
        mCanLoadMore = false;
    }

    @Override
    public void setLoadMoreComplete(boolean hasMore) {
        mCanLoadMore = hasMore;
    }

    @Override
    public void setHasMore(boolean hasMore) {
        mHasMore = hasMore;
        mRecyclerAdapterWraper.setHasMore(mHasMore);
    }

    @Override
    public void enableRefresh() {
        mCanRefresh = true;
    }

    @Override
    public void disableRefresh() {
        mCanRefresh = false;
    }

    @Override
    public boolean hasMore() {
        return mHasMore;
    }

    @Override
    public void startRefresh() {
        if (mIRefreshListener != null) {
            mIRefreshListener.onRefresh();
        }
    }

    @Override
    public void completeRefresh() {
        if (mCurrentState == State.DRAGGING)
            return;
        flingToTop(mRefreshHeaderView.getPaddingTop(), -mRefreshHeight);
        mCurrentState = State.NORMAL;
    }

    @Override
    public void startLoadMore() {
        if (mRecyclerAdapterWraper != null) {
            mRecyclerAdapterWraper.startLoadMore();
        }
        if (mIRefreshListener != null) {
            mIRefreshListener.onLoadMore();
        }
    }

    @Override
    public void completeLoadMore() {
        if (mRecyclerAdapterWraper != null) {
            mRecyclerAdapterWraper.completeLoadMore();
        }
    }

    enum State {
        NORMAL(0),
        DRAGGING(1),
        REFRESHING(2),
        LOADINGMORE(3);
        int mState;

        State(int value) {
            mState = value;
        }
    }
}
