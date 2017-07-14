package com.daystudy.daystudy.picker.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.daystudy.daystudy.R;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/5/8
 */

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_ac);
        findViewById(R.id.one_picker).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this,OnePickerActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.list_picker).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this,MyActivity.class);
            startActivity(intent);
        });
    }
}
