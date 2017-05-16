package com.daystudy.daystudy.day3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daystudy.daystudy.R;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/5/9
 * 深入理解Android中自定义View属性
 * 1.attrs.xml里面的declare-styleable以及item，android会根据其在R.java中生成一些常量方便我们使用(aapt干的)，
 * 本质上，我们可以不声明declare-styleable仅仅声明所需的属性即可。
 * 2.我们在View的构造方法中，可以通过AttributeSet去获得自定义属性的值，但是比较麻烦，而TypedArray可以很方便的便于我们去获取。
 * 3.我们在自定义View的时候，可以使用系统已经定义的属性。
 *
 */

public class MyView extends LinearLayout{

    private static final String TAG = MyView.class.getSimpleName();
    private String mString;
    private Drawable mDrawable;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getMyAttrsByAttributeSet(attrs);
        getMyAttrsByTypeArray(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.myview_item, this, true);
        TextView tv = (TextView) findViewById(R.id.tv);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        tv.setText(mString);
        iv.setBackgroundDrawable(mDrawable);
        iv1.setBackgroundResource(R.drawable.m_community_setting_next_step_arrow);

    }

    private void getMyAttrsByTypeArray(Context context,AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        mString = array.getString(R.styleable.MyView_android_text);
        mDrawable = array.getDrawable(R.styleable.MyView_bg);
        array.recycle();
        Log.i(TAG,"string: " + mString +"---drawable: " + mDrawable);
    }

    private void getMyAttrsByAttributeSet(AttributeSet attrs) {
        for (int i = 0; i < attrs.getAttributeCount() ; i++) {
            String name = attrs.getAttributeName(i);
            String value = attrs.getAttributeValue(i);
            Log.i(TAG,"name: " + name +"---value: " + value);
        }

    }


}
