package com.daystudy.daystudy.material.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daystudy.daystudy.material.fragments.FragmentFactory;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/5/22
 */

public class TableAdapter extends FragmentPagerAdapter {

    private String[] indicators;

    public TableAdapter(FragmentManager fm,String[] indicators) {
        super(fm);
        this.indicators = indicators;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getInstance(position);
    }

    @Override
    public int getCount() {
        return indicators.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return indicators[position];
    }
}
