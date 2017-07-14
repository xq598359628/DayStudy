package com.daystudy.daystudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/21
 */

public class TestView extends View {


    private Paint mPaint;
    private LinearGradient mGradient;
    private int mWidth;
    private int mHeight;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wideMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if (wideMode == MeasureSpec.UNSPECIFIED || wideMode == MeasureSpec.AT_MOST) {
            mWidth = screenWidth;
            if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
               mHeight = screenHeight;
            }
        }
        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradient == null) {
            mGradient = new LinearGradient(0,0,mWidth/2,mHeight/2,new int[]{Color.RED,Color.BLUE,Color.YELLOW},null, Shader.TileMode.MIRROR);
        }
        mPaint.setShader(mGradient);
        canvas.drawRect(0,0,mWidth,mHeight,mPaint);
    }
}
