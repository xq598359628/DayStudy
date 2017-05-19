package com.daystudy.daystudy.day4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daystudy.daystudy.R;

/**
 * 学习实时监听Edittext字符输入字数，超过字符限制提示
 *
 *
 */
public class EdittextListenerActivity extends AppCompatActivity {

    private CharSequence temp;
    private EditText mEt;
    private TextView mTv;
    private TextView mTv2;

    private static final int limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_listener);
        mEt = (EditText) findViewById(R.id.et);
        mTv = (TextView) findViewById(R.id.tv);
        mTv2 = (TextView) findViewById(R.id.tv2);
        mEt.addTextChangedListener(new TextWatcher() {

            private int mEnd;
            private int mStart1;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //记录输入后变化的的文本，此时的s已经是输入后变化的本文了
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //此时的s已经是输入后变化的本文了
                mTv2.setText("还能输入" + (limit - s.length()) + "字符");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //记录每次操作后的光标的起始结束位置， 这里的start 肯定==end
                mStart1 = mEt.getSelectionStart();
                mEnd = mEt.getSelectionEnd();

                mTv.setText("您输入了" + temp.length() + "个字符");
                if (temp.length() > limit) {
                    Toast.makeText(EdittextListenerActivity.this, "字符超出限制了", Toast.LENGTH_SHORT).show();
                    s.delete(mStart1-1,mEnd);//删除多余的字符
                    int tempSelection = mStart1;
                    mEt.setText(s);
                    //将光标放到字符限制
                    mEt.setSelection(tempSelection);
                }
            }
        });
    }
}
