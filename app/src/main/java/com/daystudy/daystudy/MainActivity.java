package com.daystudy.daystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void feature_1(){
        Toast.makeText(this, "feature-1", Toast.LENGTH_SHORT).show();
    }

    /**
     * feature_2分支提交的代码
     */
    private void feature_2() {
        Toast.makeText(this, "feature-2", Toast.LENGTH_SHORT).show();
    }


}
