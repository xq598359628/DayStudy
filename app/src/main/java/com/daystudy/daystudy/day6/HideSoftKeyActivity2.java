package com.daystudy.daystudy.day6;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/6/2
 */

public class HideSoftKeyActivity2 extends AppCompatActivity{


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            View focusView = getCurrentFocus();
            if (isShouldHideKeyboard(focusView,ev)) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception e) {
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View view, MotionEvent ev) {
        if (view != null && (view instanceof EditText)) {
            int[] location = {0,0};
            view.getLocationInWindow(location);
            int left = location[0];
            int top = location[1];
            int right = left + view.getWidth();
            int bottom = top + view.getHeight();
            return !(ev.getX() > left && ev.getX() < right && ev.getY()<bottom && ev.getX()>top);
        }
        return false;
    }

}
