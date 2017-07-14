package com.daystudy.daystudy.material.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.daystudy.daystudy.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private int mPreviousTab = -1; // 记录点击tab前的fragment索引标号
    Fragment1 mFragment1;
    Fragment2 mFragment2;
    Fragment3 mFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        changeFragment(1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int postion = 1;
        if (id == R.id.btn1) {
            postion = 1;
        } else if (id == R.id.btn2) {
            postion = 2;
        }else if (id == R.id.btn3) {
            postion = 3;
        }
        changeFragment(postion);
    }


    private void changeFragment(int position) {
        if (position < 1 || position > 3) {
            // no move
        }  else if (position != mPreviousTab) {
            showCurrentFragment(position);
            mPreviousTab = position;
        }
    }



    private void showCurrentFragment(int position) {
        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
        hidenFragments(tran);
        switch (position) {
            case 1:
                if (null == mFragment1) {
                    mFragment1 = new Fragment1();
                    tran.add(R.id.container, mFragment1);
                }
                tran.show(mFragment1);
                break;
            case 2:
                if (null == mFragment2) {
                    mFragment2 = new Fragment2();
                    tran.add(R.id.container, mFragment2);
                }
                tran.show(mFragment2);
                break;
            case 3:
                if (null == mFragment3) {
                    mFragment3 = new Fragment3();
                    tran.add(R.id.container, mFragment3);
                }
                tran.show(mFragment3);
                break;
            default:
                break;
        }
        tran.commitAllowingStateLoss();
    }

    private void hidenFragments(FragmentTransaction tran) {
        if (null != mFragment1) {
            tran.hide(mFragment1);
        }
        if (null != mFragment2) {
            tran.hide(mFragment2);
        }
        if (null != mFragment3) {
            tran.hide(mFragment3);
        }
    }
}
