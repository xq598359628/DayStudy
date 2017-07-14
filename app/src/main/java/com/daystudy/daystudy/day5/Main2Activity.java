package com.daystudy.daystudy.day5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daystudy.daystudy.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
/*        ViewGroup rootView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.item_img,null);
        rootView.addView(view);*/

        mTv = (TextView) findViewById(R.id.m_community_health_me_user_praise);
        mTv.setOnClickListener(this);
        mTv.setText(formateUserAction("18\n被点赞"));
    }

    @NonNull
    private SpannableString formateUserAction(String content) {
        SpannableString spannableString = new SpannableString(content);
        int indexOfSpace = content.indexOf("\n");
        spannableString.setSpan(new AbsoluteSizeSpan(15,true),0,indexOfSpace, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(12,true),indexOfSpace,content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color_1)),0,indexOfSpace,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color_3)),indexOfSpace,content.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public void click(View view){
        Toast.makeText(this, "text", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}
