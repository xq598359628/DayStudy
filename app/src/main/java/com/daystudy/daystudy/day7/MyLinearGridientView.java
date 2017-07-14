package com.daystudy.daystudy.day7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/21
 */

public class MyLinearGridientView extends View {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private int mStart = 0XFF8080FF;
    private int mEnd = 0XFFFF8080;
    private LinearGradient mGradient;


    public MyLinearGridientView(Context context) {
        this(context, null);
    }

    public MyLinearGridientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyLinearGridientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mWidth, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mHeight, getResources().getDisplayMetrics()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradient == null) {
            mGradient = new LinearGradient(0, 0, mWidth, mHeight, mStart,mEnd, Shader.TileMode.MIRROR);
        }

        mPaint.setShader(mGradient);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(), mPaint);
    }

    public void setGridient(int start,int end){
        this.mStart = start;
        this.mEnd = end;
        mGradient = new LinearGradient(0, 0, mWidth, mHeight, mStart,mEnd, Shader.TileMode.MIRROR);
        invalidate();
    }
}
