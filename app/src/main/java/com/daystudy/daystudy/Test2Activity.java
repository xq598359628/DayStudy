package com.daystudy.daystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.daystudy.daystudy.day7.NameCardDialog;

public class Test2Activity extends AppCompatActivity implements View.OnClickListener {


    private BadgeView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        View message = findViewById(R.id.community_home_title);
        mView = new BadgeView(this);
        mView.setTargetView(message);
        mView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        mView.setBadgeCount(110);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameCardDialog dialog = new NameCardDialog(Test2Activity.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
