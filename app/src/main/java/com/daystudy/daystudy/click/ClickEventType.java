package com.daystudy.daystudy.click;

import android.app.Activity;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/30
 */

public abstract class ClickEventType {
    protected Activity mActivity;

    public abstract void click();

    public Activity getActivity() {
        return mActivity;
    }


}