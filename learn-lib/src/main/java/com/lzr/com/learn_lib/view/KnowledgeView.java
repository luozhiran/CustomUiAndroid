package com.lzr.com.learn_lib.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.interfaces.KnowledgeChangeModeCallback;

public class KnowledgeView extends FrameLayout implements View.OnClickListener{

    private ImageView mImgBtn;
    private ValueAnimator mImgBtnAnimationZoomIn;
    private ValueAnimator mImgBtnAnimationZoomOut;
    private ImageView mLeftWav;
    private KnowledgeChangeModeCallback mKnowledgeChangeModeCallback;
    public final static int S_T_M = 1;//开始->麦克风
    public final static int S_T_P = 2;//开始->暂停
    public final static int P_T_S = 3;//暂停->开始
    public final static int P_T_M = 4;//暂停->麦克风
    public final static int M_T_S = 5;//麦克风->开始
    public final static int P_T_P = 6;//麦克风->暂停
    private int mMode = 0;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    smallAnimation();
                    smallAnimationRight();
                    break;
                case 2:
                    mLeftWav.setVisibility(GONE);
                    mRightWav.setVisibility(GONE);
                    break;
            }

            return false;
        }
    });
    private FrameLayout mFlStart;
    private ImageView mRightWav;

    public KnowledgeView(@NonNull Context context) {
        this(context, null);
    }

    public KnowledgeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.knowledge_view, null, false);
        mImgBtn = itemView.findViewById(R.id.btn_img);
        mImgBtn.setImageResource(R.mipmap.ic_start_btn);
        mImgBtn.setTag(R.mipmap.ic_start_btn);
        mImgBtn.setOnClickListener(this);
        this.addView(itemView);
        initValueStartZoomMic();
        mLeftWav = itemView.findViewById(R.id.wav_left);
        mFlStart = (FrameLayout) itemView.findViewById(R.id.start_fl);
        mRightWav = (ImageView) itemView.findViewById(R.id.wav_right);
    }

    private void initValueStartZoomMic() {
        mImgBtnAnimationZoomIn = ValueAnimator.ofFloat(1, 0.3f);
        mImgBtnAnimationZoomIn.setDuration(200);
        mImgBtnAnimationZoomIn.setInterpolator(new DecelerateInterpolator());
        mImgBtnAnimationZoomIn.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mImgBtnAnimationZoomIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mImgBtn.setAlpha(value);
                mImgBtn.setScaleX(value);
                mImgBtn.setScaleY(value);
            }
        });

        mImgBtnAnimationZoomOut = ValueAnimator.ofFloat(0.3f, 1);
        mImgBtnAnimationZoomOut.setDuration(100);
        mImgBtnAnimationZoomOut.setInterpolator(new DecelerateInterpolator());
        mImgBtnAnimationZoomOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mImgBtnAnimationZoomOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mImgBtn.setAlpha(value);
                mImgBtn.setScaleX(value);
                mImgBtn.setScaleY(value);
            }
        });

    }


    private void animationEnd() {
        switch (mMode) {
            case S_T_M://开始->麦克风
                mImgBtn.setImageResource(R.mipmap.ic_mic);
                break;
            case S_T_P://开始->暂停
                mImgBtn.setImageResource(R.mipmap.ic_pause_btn);
                break;
            case P_T_S://暂停->开始
                mImgBtn.setImageResource(R.mipmap.ic_start_btn);
                break;
            case P_T_M: //暂停->麦克风
                mImgBtn.setImageResource(R.mipmap.ic_mic);
                break;
            case M_T_S://麦克风->开始
                mImgBtn.setImageResource(R.mipmap.ic_start_btn);
                break;
            case P_T_P://麦克风->暂停
                mImgBtn.setImageResource(R.mipmap.ic_pause_btn);
                break;
        }
        mImgBtnAnimationZoomOut.start();
    }

    /**
     * 开始转化麦克风
     */
    public void startZoomInMic(int mode) {
        if (mImgBtnAnimationZoomOut.isRunning() || mImgBtnAnimationZoomIn.isRunning()) {
            return;
        }
        mMode = mode;
        switch (mode) {
            case S_T_M://开始->麦克风
                mImgBtn.setImageResource(R.mipmap.ic_start_btn);
                enterAnimation();
                enterAnimationRight();
                break;
            case S_T_P://开始->暂停
                mImgBtn.setImageResource(R.mipmap.ic_start_btn);
                break;
            case P_T_S://暂停->开始
                mImgBtn.setImageResource(R.mipmap.ic_pause_btn);
                break;
            case P_T_M://暂停->麦克风
                mImgBtn.setImageResource(R.mipmap.ic_pause_btn);
                enterAnimation();
                enterAnimationRight();
                break;
            case M_T_S://麦克风->开始
                mImgBtn.setImageResource(R.mipmap.ic_mic);
                quiteAnimation();
                quiteAnimationRight();
                break;
            case P_T_P://麦克风->暂停
                mImgBtn.setImageResource(R.mipmap.ic_mic);
                quiteAnimation();
                quiteAnimationRight();
                break;
        }

        mImgBtnAnimationZoomIn.start();
    }

    public int getMode(){
        return mMode;
    }

    private void enterAnimation() {
        mLeftWav.setImageResource(R.drawable.enter_anim);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mLeftWav.getDrawable();
        mLeftWav.setVisibility(VISIBLE);
        mEnterAnimation.start();
        int duration = 0;
        for (int i = 0; i < mEnterAnimation.getNumberOfFrames(); i++) {
            duration += mEnterAnimation.getDuration(i);
        }
        mHandler.sendEmptyMessageDelayed(1, duration + 800);
    }

    private void enterAnimationRight() {
        mRightWav.setImageResource(R.drawable.enter_anim_right);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mRightWav.getDrawable();
        mRightWav.setVisibility(VISIBLE);
        mEnterAnimation.start();
        int duration = 0;
        for (int i = 0; i < mEnterAnimation.getNumberOfFrames(); i++) {
            duration += mEnterAnimation.getDuration(i);
        }
        mHandler.sendEmptyMessageDelayed(1, duration + 800);
    }

    private void quiteAnimation() {
        mLeftWav.setImageResource(R.drawable.quit_anim);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mLeftWav.getDrawable();
        mLeftWav.setVisibility(VISIBLE);
        mEnterAnimation.start();
        int duration = 0;
        for (int i = 0; i < mEnterAnimation.getNumberOfFrames(); i++) {
            duration += mEnterAnimation.getDuration(i);
        }
        mHandler.sendEmptyMessageDelayed(2, duration);
    }

    private void quiteAnimationRight() {
        mRightWav.setImageResource(R.drawable.quit_anim_right);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mRightWav.getDrawable();
        mRightWav.setVisibility(VISIBLE);
        mEnterAnimation.start();
        int duration = 0;
        for (int i = 0; i < mEnterAnimation.getNumberOfFrames(); i++) {
            duration += mEnterAnimation.getDuration(i);
        }
        mHandler.sendEmptyMessageDelayed(2, duration);
    }

    private void smallAnimation() {
        mLeftWav.setImageResource(R.drawable.smale_anim);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mLeftWav.getDrawable();
        mEnterAnimation.start();
    }

    private void smallAnimationRight() {
        mRightWav.setImageResource(R.drawable.smale_anim_right);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mRightWav.getDrawable();
        mEnterAnimation.start();
    }

    private void bigAnimation() {
        mLeftWav.setImageResource(R.drawable.big_anim);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mLeftWav.getDrawable();
        mEnterAnimation.start();
    }

    private void bigAnimationRight() {
        mRightWav.setImageResource(R.drawable.big_anim);
        AnimationDrawable mEnterAnimation = (AnimationDrawable) mRightWav.getDrawable();
        mEnterAnimation.start();
    }

    @Override
    public void onClick(View v) {
        if (mKnowledgeChangeModeCallback!=null) {
            mKnowledgeChangeModeCallback.changeMode(mMode);
        }
    }

    public void setKnowledgeChangeModeCallback(KnowledgeChangeModeCallback knowledgeChangeModeCallback) {
        this.mKnowledgeChangeModeCallback = knowledgeChangeModeCallback;
    }
}
