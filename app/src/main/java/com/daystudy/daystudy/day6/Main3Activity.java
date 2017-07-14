package com.daystudy.daystudy.day6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.daystudy.daystudy.R;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText et = (EditText) findViewById(R.id.et);
        String s = "sdfsdfs";
        et.setText(s);
        et.setSelection(s.length());
    }
}
