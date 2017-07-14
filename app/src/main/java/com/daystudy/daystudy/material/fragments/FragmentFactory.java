package com.daystudy.daystudy.material.fragments;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/5/22
 */

public class FragmentFactory {

    private static SparseArray<Fragment> mSparseArray = new SparseArray<>();

    public static Fragment getInstance(int position) {
        Fragment fragment = mSparseArray.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new Fragment1();
                    break;
                case 1:
                    fragment = new Fragment2();
                    break;
                case 2:
                    fragment = new Fragment3();
                    break;
                case 3:
                    fragment = new Fragment4();
                    break;
                default:
                    break;
            }
            mSparseArray.put(position,fragment);
        }
        return fragment;
    }
}
