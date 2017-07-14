package com.daystudy.daystudy.pulltorefresh;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: hzlishang
 * Data: 16/9/7 下午7:14
 * Des:
 * version:
 */
public class RecyclerAdapterWraper extends RecyclerView.Adapter implements ILoadMore {

    private RecyclerView.Adapter mInnerAdapter;
    private View mLoadMoreView;
    private boolean mHasMore;
    private boolean isLoadingMore;

    interface Type {
        int NORMAL_ITEM = 1;
        int LOADMORE_ITEM = 2;
    }

    public RecyclerAdapterWraper(RecyclerView.Adapter adapter, View loadmoreView) {
        mInnerAdapter = adapter;
        mLoadMoreView = loadmoreView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Type.LOADMORE_ITEM == viewType) {
            return new LoadMoreFooterViewHolder(mLoadMoreView);
        } else {
            return mInnerAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof LoadMoreFooterViewHolder)) {
            mInnerAdapter.onBindViewHolder(holder, position);
        } else {
            LoadMoreFooterViewHolder footerViewHolder = (LoadMoreFooterViewHolder) holder;
            if (isLoadingMore) {
                footerViewHolder.startLoadMore();
            } else {
                footerViewHolder.completeLoadMore();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position >= mInnerAdapter.getItemCount() ? Type.LOADMORE_ITEM : Type.NORMAL_ITEM;
    }

    @Override
    public int getItemCount() {
        if (mHasMore) {
            return mInnerAdapter.getItemCount() + 1;
        } else {
            return mInnerAdapter.getItemCount();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isFooter(position) && mHasMore ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (isFooter(position) && mHasMore) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }

    private boolean isFooter(int postion) {
        return postion == getItemCount() - 1;
    }

    public void setHasMore(boolean hasMore) {
        mHasMore = hasMore;
        notifyDataSetChanged();
        completeLoadMore();
    }

    @Override
    public void startLoadMore() {

        isLoadingMore = true;
        notifyDataSetChanged();
    }

    @Override
    public void completeLoadMore() {
        isLoadingMore = false;
        notifyDataSetChanged();
    }
}
