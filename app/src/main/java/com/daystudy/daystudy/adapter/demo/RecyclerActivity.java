package com.daystudy.daystudy.adapter.demo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.daystudy.daystudy.R;

import java.util.Arrays;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        setSupportActionBar(mToolbar);
        ContentFragmentVP vp = new ContentFragmentVP(getSupportFragmentManager());
        mViewPager.setAdapter(vp);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).select();
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(this, tab.getPosition() + "---" + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private class ContentFragmentVP extends FragmentPagerAdapter{

        private List<Fragment> mList = Arrays.asList(new RvFragment(),new Rv2Fragment(),new Rv3Fragment());


        public ContentFragmentVP(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getClass().getSimpleName();
        }
    }

}
