package com.daystudy.daystudy.day5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * 上拉加载更多的自定义控件
 * Created by shenxl on 2016/6/14.
 */
public class CustomLoadingView extends View {
    private static final int PERIOD = 200; //相邻两点开始动画的时间间隔
    private static final int ANIM_TIME = 1000; //一个点旋转一圈的时间
    private static final int DOT_NUM = 5; //点数
    private static final int DURATION = PERIOD * DOT_NUM + ANIM_TIME; //单次动画总时间

    private int mDotColor = Color.parseColor("#999999"); //点颜色
    private int dotRadius; //点半径
    private int cx, cy, radius; //轨迹圆的圆心与半径
    private int currentTime = 0;
    private Paint mPaint;
    private BarAnimation anim;


    public CustomLoadingView(Context context) {
        super(context);
    }

    public CustomLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        dotRadius = dip2px(2.5f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mDotColor);
        anim = new BarAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cx = getMeasuredWidth() / 2;
        cy = getMeasuredHeight() / 2;
        radius = cx > cy ? cy : cx;
        radius -= dotRadius;

        int timeOffset = 0;
        for (int i = 0; i < DOT_NUM; i++) {
            drawCircle(canvas, getAngle(timeOffset));
            timeOffset += PERIOD;
        }
    }

    private double getAngle(int timeOffset) {
        if (currentTime <= timeOffset || currentTime >= timeOffset + ANIM_TIME) {
            return 0;
        } else {
            return getAcceleratedValue(currentTime - timeOffset) * Math.PI * 2;
        }
    }

    private static final float a = 0.5f; //加速度因子

    /**
     * 减速公式
     *
     * @param ori
     * @return
     */
    private double getAcceleratedValue(double ori) {
        double v = ori / ANIM_TIME;
        return (1 + a - a * v * v) * v;
    }

    private void drawCircle(Canvas canvas, double angle) {
        float dx = (float) (cx + radius * Math.sin(angle));
        float dy = (float) (cy - radius * Math.cos(angle));
        canvas.drawCircle(dx, dy, dotRadius, mPaint);
    }

    private int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private class BarAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                currentTime = (int) (interpolatedTime * DURATION);
                postInvalidate();
            } else {
                currentTime = 0;
            }
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(DURATION);
            setRepeatCount(INFINITE);
            setFillAfter(true);
            setInterpolator(new LinearInterpolator());
        }
    }

    public void pullY(float y) {
        if (getAnimation() == null) {
            this.startAnimation(anim);
        }
    }

    public void startRefresh() {
        if (getAnimation() == null) {
            this.startAnimation(anim);
        }
    }

    public void endRefresh() {
        if (anim.hasStarted()) {
            this.clearAnimation();
        }
    }

    public void reset() {
    }

    public View getViewImp() {
        return this;
    }
}
