package com.daystudy.daystudy.day3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.daystudy.daystudy.R;

public class TimeLineActivity extends AppCompatActivity {

    private TimelineLayout mTimeLineLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        mTimeLineLayout = (TimelineLayout) findViewById(R.id.timeline_layout);
        for (int i = 0; i < 3 ; i++) {
            addItem();
        }

    }

    private void addItem(){
        View view = LayoutInflater.from(this).inflate(R.layout.item_timeline, mTimeLineLayout, false);
        ((TextView) view.findViewById(R.id.tv_action)).setText("步骤3");
        ((TextView) view.findViewById(R.id.tv_action_time)).setText("2017年3月8日16:55:04");
        ((TextView) view.findViewById(R.id.tv_action_status)).setText("完成");
        mTimeLineLayout.addView(view);
    }
}
