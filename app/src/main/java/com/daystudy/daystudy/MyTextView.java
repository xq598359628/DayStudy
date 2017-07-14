package com.daystudy.daystudy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/30
 */

public class MyTextView extends View {

    private TextPaint mPaint;
    private String mText;
    private int mWidth;
    private int mHeight;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new TextPaint();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText = array.getString(R.styleable.MyTextView_android_text);
        float textSize = array.getDimensionPixelSize(R.styleable.MyTextView_android_textSize, 13);
        array.recycle();

        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        int resultW = mWidth;
        int resultH = mHeight;

        int contentW = 0, contentH = 0;
        if (widthMode == MeasureSpec.AT_MOST) {
            if (!TextUtils.isEmpty(mText)) {
                contentW = (int) (mPaint.measureText(mText) + getPaddingLeft() + getPaddingRight() + 0.5f);
                resultW = contentW > mWidth ? mWidth : contentW;
            }
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            if (!TextUtils.isEmpty(mText)) {
                contentH += getPaddingTop() + getPaddingBottom();
                resultH = contentH > mHeight ? mHeight : contentH;
            }
        }

        setMeasuredDimension(resultW,resultH);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(mText)) {
            return;
        }
        canvas.drawColor(Color.BLUE);

        float x = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        float y = (getHeight() - getPaddingTop() - getPaddingBottom()) / 2 + mPaint.getFontMetrics().descent;
        Log.i("TAG", "getWidth : " + getWidth() + "---" + getHeight() + "---" + getPaddingRight() + "---" + getPaddingLeft());

        canvas.drawText(mText, x, y, mPaint);
    }
}
