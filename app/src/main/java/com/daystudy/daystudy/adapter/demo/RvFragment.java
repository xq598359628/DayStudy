package com.daystudy.daystudy.adapter.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daystudy.daystudy.R;
import com.daystudy.daystudy.adapter.BGADivider;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/23
 */

public class RvFragment extends Fragment {
    private static final String TAG = RvFragment.class.getSimpleName();
    private NormalRecyclerViewAdapter mAdapter;
    private RecyclerView mDataRv;
    private ItemTouchHelper mItemTouchHelper;
    private BGABanner mBanner;
    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_fg,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = (RecyclerView) view.findViewById(R.id.rv_fg1);
        mRv.addItemDecoration(BGADivider.newBitmapDivider().setStartSkipCount(1).setEndSkipCount(1));
        // 初始化拖拽排序和滑动删除
        mItemTouchHelper.attachToRecyclerView(mDataRv);

    }
}
