package com.daystudy.daystudy.day7;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daystudy.daystudy.R;
import com.daystudy.daystudy.dialog.widget.base.BaseDialog;


/**
 * Description:堂名片对话框
 * Author     : xq
 * Date       : 2016/12/20
 */
public class NameCardDialog extends BaseDialog<NameCardDialog> implements View.OnClickListener {

    private ImageView mClose;
    private ImageView mHead;
    private ImageView mcamera;
    private ProgressBar mLoading;
    private FrameLayout mHeadLay;
    private EditText mEtNickname;
    private Button mBtnNext;
    private TextView mHintNickname;

    private NameCardListener listener;
    private String relativeUrl;

    public NameCardDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        heightScale(0.8f);
        dimEnabled(false);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F2FFFFFF")));
        View inflate = View.inflate(mContext, R.layout.m_community_namecard_dialog, null);
        mClose = (ImageView) inflate.findViewById(R.id.community_nickname_close);
        mHead = (ImageView) inflate.findViewById(R.id.community_nickname_head);
        mcamera = (ImageView) inflate.findViewById(R.id.community_nickname_camera);
        mLoading = (ProgressBar) inflate.findViewById(R.id.img_loading);
        mHeadLay = (FrameLayout) inflate.findViewById(R.id.community_nickname_head_lay);
        mEtNickname = (EditText) inflate.findViewById(R.id.community_nickname_et);
        mBtnNext = (Button) inflate.findViewById(R.id.community_nickname_next);
        mHintNickname = (TextView) inflate.findViewById(R.id.community_nickname_hint);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        mClose.setOnClickListener(this);
        mHeadLay.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        //如果名字为空，不能点击下一步
        mEtNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBtnNext.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
                restroeDefaultHint();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //监听Edittext的Action
        mEtNickname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                //键盘引导等同于点击下一步
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String name = mEtNickname.getText().toString().trim();
                    //如果名字为空，不能走一下步
                    if (!TextUtils.isEmpty(name)) {
                        listener.onNextClick(name, "", relativeUrl);
                    }

                }
                return false;
            }
        });

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim(mHeadLay, 0);
        ObjectAnimator animtor = ObjectAnimator.ofFloat(mEtNickname, "translationY", getViewHeight(mEtNickname) + dp2px(50), 0);
        animtor.setStartDelay(200);
        animtor.setDuration(800);
        //为了让动画下来的时候没有光标显示，等动画结束时再出现
        animtor.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //获取焦点 光标出现 200
                mEtNickname.requestFocus();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animtor.start();
        startAnim(mHintNickname, 300);
        startAnim(mBtnNext, 400);
    }

    private void startAnim(View view, long startDelay) {
        ObjectAnimator animtor = ObjectAnimator.ofFloat(view, "translationY", getViewHeight(view) + dp2px(50), 0);
        animtor.setStartDelay(startDelay);
        animtor.setDuration(800);
        animtor.start();
    }

    //获取控件的高度
    private float getViewHeight(View measureView) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        measureView.measure(w, h);
        return (float) measureView.getMeasuredHeight();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.community_nickname_close) {
        } else if (i == R.id.community_nickname_head_lay) {//上传图片
            if (listener != null) {
                listener.onChoosePic();

            }
        } else if (i == R.id.community_nickname_next) {
            String name = mEtNickname.getText().toString().trim();
            listener.onNextClick(name, "", relativeUrl);
        }
    }

    public void loadImage(String url, String relativeUrl) {
        this.relativeUrl = relativeUrl;

    }

    public NameCardDialog setNameCardListener(NameCardListener listener) {
        this.listener = listener;
        return this;
    }

    public void setUploadFailedText(String text) {
        mHintNickname.setText(text);
        mHintNickname.setTextColor(Color.RED);
    }

    public void setWhenUploadHeadImg(int gone,int gone2){
        mcamera.setVisibility(gone);
        mHead.setVisibility(gone);
        mLoading.setVisibility(gone2);
    }

    private void restroeDefaultHint(){
        mHintNickname.setText("头像、昵称可在个人中心修改哦~");
    }

    public interface NameCardListener {
        void onNextClick(String name, String intro, String relativeUrl);

        void onChoosePic();
    }


}