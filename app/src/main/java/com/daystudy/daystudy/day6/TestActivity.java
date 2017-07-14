package com.daystudy.daystudy.day6;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.daystudy.daystudy.R;

public class TestActivity extends HideSoftKeyActivity{
    private EditText mPhone;
    private View mBtnCode;
    private View mBtnCode1;
    private EditText mBtnCode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mBtnCode = findViewById(R.id.btn_code);
        mBtnCode1 = findViewById(R.id.et_check_code);
        mBtnCode2 = (EditText) findViewById(R.id.et_city_code);
        mPhone.setText("Sdfsdf");
        mPhone.requestFocus();
    }

    @Override
    public View[] filterViewByIds() {
        View[] views = { mPhone,mBtnCode1,mBtnCode2};
        return views;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.et_phone, R.id.et_check_code, R.id.et_city_code};
        return ids;
    }
}
