package com.daystudy.daystudy.click;

import android.app.Activity;
import android.widget.Toast;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/7/3
 */

public class ToastEvent extends ClickEventType{

    public ToastEvent(Activity activity){
        mActivity = activity;
    }

    @Override
    public void click() {
        Toast.makeText(mActivity, "点击了", Toast.LENGTH_SHORT).show();
    }
}
