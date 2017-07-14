package com.daystudy.daystudy.material.tablayout;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.daystudy.daystudy.R;

public class BottomTabLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tb);
        String[] array = getResources().getStringArray(R.array.tab_indicators);
        TypedArray drawables = getResources().obtainTypedArray(R.array.drawables);
        final ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new TableAdapter(getSupportFragmentManager(),array));
        mTabLayout.setupWithViewPager(vp);
        for (int i = 0; i < array.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.bottom_item);
                TextView view = (TextView) tab.getCustomView();
                view.setText(array[i]);
                view.setCompoundDrawablesWithIntrinsicBounds(0,drawables.getResourceId(i,0),0,0);
            }
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView view = (TextView) tab.getCustomView();
                view.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.gh_cm_icon_qqzone,0,0);
                view.setTextColor(Color.RED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.setCurrentItem(1);
    }


}
