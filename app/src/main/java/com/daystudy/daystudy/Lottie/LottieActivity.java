package com.daystudy.daystudy.Lottie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daystudy.daystudy.R;

public class LottieActivity extends AppCompatActivity {

    public static final String WAY_TO_USE = "useway";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
    }

    public void simpleUse(View view){
        Intent intent = new Intent(this, Lottie2Activity.class);
        intent.putExtra("useway",-1);
        startActivity(intent);
    }

    public void codeUse(View view) {
        Intent intent = new Intent(this, Lottie2Activity.class);
        intent.putExtra("useway", 1);
        startActivity(intent);
    }

    public void controlUse(View view) {
        Intent intent = new Intent(this, Lottie2Activity.class);
        intent.putExtra("useway", 2);
        startActivity(intent);
    }

    public void loadNetUse(View view) {
        Intent intent = new Intent(this, Lottie2Activity.class);
        intent.putExtra("useway", 3);
        startActivity(intent);
    }


}
