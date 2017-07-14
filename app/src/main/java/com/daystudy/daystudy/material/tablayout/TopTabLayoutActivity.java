package com.daystudy.daystudy.material.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.daystudy.daystudy.R;

/**
 * 学习TabLayout
 * 注意，GRAVITY_FILL需要和MODE_FIXED一起使用才有效果
 *
 *
 */
public class TopTabLayoutActivity extends AppCompatActivity {

    private TabLayout mTb;
    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mTb = (TabLayout) findViewById(R.id.tb);
        mVp = (ViewPager) findViewById(R.id.vp);
        String[] indicators = getResources().getStringArray(R.array.tab_indicators);
        TableAdapter adapter = new TableAdapter(getSupportFragmentManager(),indicators);
        mVp.setAdapter(adapter);
        mTb.setupWithViewPager(mVp);
        mTb.getTabAt(1).select();
        mTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(TopTabLayoutActivity.this, tab.getPosition() + "----" + tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }




}
