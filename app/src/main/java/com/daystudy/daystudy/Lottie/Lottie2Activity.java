package com.daystudy.daystudy.Lottie;

import android.animation.Animator;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daystudy.daystudy.R;

public class Lottie2Activity extends AppCompatActivity {

    private int mExtra;
    private LottieAnimationView mAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie2);
        mAnimView = (LottieAnimationView) findViewById(R.id.animation_view);
        Intent intent = getIntent();
        if (intent != null) {
            mExtra = intent.getIntExtra(LottieActivity.WAY_TO_USE,-1);
            processData();
        }
    }

    private void processData() {
        switch (mExtra) {
            case 1:
                mAnimView.setAnimation("lottiefiles.com - Animated Graph.json");
                mAnimView.loop(true);
                mAnimView.playAnimation();
            break;
            case 2:
                mAnimView.setAnimation("lottiefiles.com - Countdown.json");
                mAnimView.loop(true);
                mAnimView.playAnimation();
                //mAnimView.setProgress(0.5f);
                mAnimView.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Toast.makeText(Lottie2Activity.this, "精美的动画开始了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Toast.makeText(Lottie2Activity.this, "精美的动画结束了哦~~", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Toast.makeText(Lottie2Activity.this, "精美的动画被取消了哦~~", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Toast.makeText(Lottie2Activity.this, "精美的动画在重复哦~~", Toast.LENGTH_SHORT).show();
                    }
                });

                mAnimView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAnimView.isAnimating()) {
                            mAnimView.cancelAnimation();
                        }else {
                            //自定义速度
                            mAnimView.setAnimation("lottiefiles.com - Camera.json");
                            mAnimView.loop(true);
                            mAnimView.playAnimation();
                        }

                    }
                });

                break;
            case 3:
                downLoad();

                break;
            default:
                break;
        }
    }

    private void downLoad() {
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
       // String url = ""
    }
}
