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

public class Fragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("tag", "Fragment4可见了");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("tag", "Fragment4不可见了");
    }
}
