package com.daystudy.daystudy.click;

import android.app.Activity;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/30
 */

public class FinishActivityEvent extends ClickEventType {

    public FinishActivityEvent(Activity activity){
        mActivity = activity;
    }

    @Override
    public void click() {
        mActivity.finish();
    }

}
