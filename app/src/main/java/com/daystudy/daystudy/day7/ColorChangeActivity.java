package com.daystudy.daystudy.day7;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.daystudy.daystudy.R;

public class ColorChangeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TextWatcher {

    private ViewPager mVp;
    int[] imgs = {R.drawable.timg,R.drawable.timg1,R.drawable.timg3,R.drawable.timg5};
    int[] colors = {Color.RED,Color.GREEN,Color.YELLOW,Color.BLUE};
    private MyLinearGridientView mView;
    private EditText mEt;

    private float limit = 50;
    private float ratio;
    private ArgbEvaluator mEvaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_change);
        mVp = (ViewPager) findViewById(R.id.vp);
        mView = (MyLinearGridientView)findViewById(R.id.myView);
        mVp.setAdapter(new MyAdapter());
        mVp.setCurrentItem(0);
        mVp.addOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ArgbEvaluator evaluator1 = new ArgbEvaluator();
        ArgbEvaluator evaluator2 = new ArgbEvaluator();
        int evaluate1 = (int) evaluator1.evaluate(positionOffset, 0XFF8080FF, 0XFFFF8080);
        int evaluate2 = (int) evaluator2.evaluate(positionOffset, 0XFFFF8080, 0XFF8080FF);
        Log.i("ColorChangeActivity","滑动了" + position +"----" + positionOffset);
        position = position % imgs.length;
        if (position % 2 == 0) {
            mView.setGridient(0XFF8080FF,0XFFFF8080);
            mView.setGridient(evaluate1,evaluate2);
        }else{
            mView.setGridient(0XFFFF8080,0XFF8080FF);
            mView.setGridient(evaluate2,evaluate2);
        }
        mEvaluator = new ArgbEvaluator();

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        ratio = s.toString().length() / limit;


    }

    class MyAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % imgs.length;
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.vp_layout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setBackgroundResource(imgs[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            
        }


    }
}
