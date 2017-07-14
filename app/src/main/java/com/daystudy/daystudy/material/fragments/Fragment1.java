package com.daystudy.daystudy.material.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daystudy.daystudy.R;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/5/22
 */

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("tag","Fragment1---onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("tag","Fragment1--onStop");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("tag","Fragment1---onHiddenChanged: " + hidden);
    }
}
