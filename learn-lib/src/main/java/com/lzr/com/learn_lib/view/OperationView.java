package com.lzr.com.learn_lib.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lzr.com.learn_lib.R;


public class OperationView extends FrameLayout implements View.OnClickListener {

    public static final int FOOLOW_UP = 1;
    public static final int ALOUD_READING = 2;
    public static final int DIALOGUE = 3;
    public static final int RECITE = 4;
    public static final int DEFAULE = 0;


    private ImageView mUpImgFollow;
    private ImageView mAloudImgReading;
    private ImageView mImgDialogue;
    private ImageView mImgRecite;
    private ImageView mBg;
    private ValueAnimator mUpImgFollowAnim, mUpImgFollowAnimBack;
    private ValueAnimator mAloudImgReadingAnim, mAloudImgReadingAnimBack;
    private ValueAnimator mImgDialogueAnim, mImgDialogueAnimBack;
    private ValueAnimator mImgReciteAnim, mImgReciteAnimBack;
    private int mCurrentAnim;

    private float mTotalHeight;
    private LinearLayout mLlBox;


    public OperationView(@NonNull Context context) {
        this(context, null);
    }

    public OperationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.operation_view, null, false);
        this.addView(itemView);
        mUpImgFollow = itemView.findViewById(R.id.follow_up_img);
        mAloudImgReading = itemView.findViewById(R.id.reading_aloud_img);
        mImgDialogue = itemView.findViewById(R.id.dialogue_img);
        mImgRecite = itemView.findViewById(R.id.recite_img);
        mBg = (ImageView) itemView.findViewById(R.id.bg);
        mUpImgFollow.setOnClickListener(this);
        mUpImgFollow.setTag(FOOLOW_UP);
        mAloudImgReading.setOnClickListener(this);
        mAloudImgReading.setTag(ALOUD_READING);
        mImgDialogue.setOnClickListener(this);
        mImgDialogue.setTag(DIALOGUE);
        mImgRecite.setOnClickListener(this);
        mImgRecite.setTag(RECITE);
        initImgFollowAnim();
        initAloudImgReadingAnim();
        initImgDialogueAnim();
        initImgReciteAnim();

        initImgFollowAnimBack();
        initAloudImgReadingBack();
        initImgReciteAnimBack();
        initImgDialogueAnimBack();
//        visiable();
        defaault();
        mLlBox = (LinearLayout) itemView.findViewById(R.id.box_ll);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalHeight = mUpImgFollow.getMeasuredHeight() + mAloudImgReading.getMeasuredHeight() + mImgDialogue.getMeasuredHeight() + mImgRecite.getMeasuredHeight();
    }


    private void defaault() {
        ValueAnimator mUpImgFollowAnim = ObjectAnimator.ofFloat(0, 1);
        mUpImgFollowAnim.setDuration(10);
        mUpImgFollowAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mUpImgFollow.setTranslationY((mTotalHeight - mUpImgFollow.getHeight()) * value);
                mAloudImgReading.setAlpha(0.5f - value);
                mImgDialogue.setAlpha(0.5f - value);
                mImgRecite.setAlpha(0.5f - value);
                mBg.setAlpha(0.5f - value);
            }
        });
        mUpImgFollowAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAloudImgReading.setVisibility(INVISIBLE);
                mImgDialogue.setVisibility(INVISIBLE);
                mImgRecite.setVisibility(INVISIBLE);
                mLlBox.setVisibility(VISIBLE);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mCurrentAnim = FOOLOW_UP;
        mUpImgFollowAnim.start();
    }

    private void initImgFollowAnim() {
        mUpImgFollowAnim = ObjectAnimator.ofFloat(0, 1);
        mUpImgFollowAnim.setDuration(500);
        mUpImgFollowAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mUpImgFollow.setTranslationY((mTotalHeight - mUpImgFollow.getHeight()) * value);
                mAloudImgReading.setAlpha(0.5f - value);
                mImgDialogue.setAlpha(0.5f - value);
                mImgRecite.setAlpha(0.5f - value);
                mBg.setAlpha(0.5f - value);
            }
        });
        mUpImgFollowAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAloudImgReading.setVisibility(INVISIBLE);
                mImgDialogue.setVisibility(INVISIBLE);
                mImgRecite.setVisibility(INVISIBLE);
                mLlBox.setVisibility(VISIBLE);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initAloudImgReadingAnim() {
        mAloudImgReadingAnim = ObjectAnimator.ofFloat(0, 1);
        mAloudImgReadingAnim.setDuration(500);
        mAloudImgReadingAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mAloudImgReading.setTranslationY((mTotalHeight - mUpImgFollow.getHeight() - mAloudImgReading.getHeight()) * value);
                mImgDialogue.setAlpha(0.5f - value);
                mUpImgFollow.setAlpha(0.5f - value);
                mImgRecite.setAlpha(0.5f - value);
                mBg.setAlpha(0.5f - value);
            }
        });
        mAloudImgReadingAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mUpImgFollow.setVisibility(INVISIBLE);
                mImgDialogue.setVisibility(INVISIBLE);
                mImgRecite.setVisibility(INVISIBLE);
                mLlBox.setVisibility(VISIBLE);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initImgDialogueAnim() {
        mImgDialogueAnim = ObjectAnimator.ofFloat(0, 1);
        mImgDialogueAnim.setDuration(300);
        mImgDialogueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mUpImgFollow.setAlpha(0.5f - value);
                mAloudImgReading.setAlpha(0.5f - value);
                mImgDialogue.setTranslationY((mTotalHeight - mUpImgFollow.getHeight() - mAloudImgReading.getHeight() - mImgDialogue.getHeight()) * value);
                mImgRecite.setAlpha(0.5f - value);
                mBg.setAlpha(0.5f - value);
            }
        });
        mImgDialogueAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mUpImgFollow.setVisibility(INVISIBLE);
                mAloudImgReading.setVisibility(INVISIBLE);
                mImgRecite.setVisibility(INVISIBLE);
                mLlBox.setVisibility(VISIBLE);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initImgReciteAnim() {
        mImgReciteAnim = ObjectAnimator.ofFloat(0, 1);
        mImgReciteAnim.setDuration(500);
        mImgReciteAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mUpImgFollow.setAlpha(0.5f - value);
                mAloudImgReading.setAlpha(0.5f - value);
                mImgDialogue.setAlpha(0.5f - value);
                mBg.setAlpha(0.5f - value);
                mImgRecite.setTranslationY((mTotalHeight - mUpImgFollow.getHeight() - mAloudImgReading.getHeight() - mImgDialogue.getHeight() - mImgRecite.getHeight()) * value);
            }
        });
        mImgReciteAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mUpImgFollow.setVisibility(INVISIBLE);
                mAloudImgReading.setVisibility(INVISIBLE);
                mImgDialogue.setVisibility(INVISIBLE);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
                mLlBox.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void initImgFollowAnimBack() {
        mUpImgFollowAnimBack = ObjectAnimator.ofFloat(1, 0);
        mUpImgFollowAnimBack.setDuration(500);
        mUpImgFollowAnimBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mUpImgFollow.setTranslationY((mTotalHeight - mUpImgFollow.getHeight()) * value);
                float alpha = 1f - value - 0.4f;
                mAloudImgReading.setAlpha(alpha);
                mImgDialogue.setAlpha(alpha);
                mImgRecite.setAlpha(alpha);
                mBg.setAlpha(1f - value + 0.5f);

            }
        });
        mUpImgFollowAnimBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mLlBox.setVisibility(INVISIBLE);
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAloudImgReading.setAlpha(1f);
                mImgDialogue.setAlpha(1f);
                mImgRecite.setAlpha(1f);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initAloudImgReadingBack() {
        mAloudImgReadingAnimBack = ObjectAnimator.ofFloat(1, 0);
        mAloudImgReadingAnimBack.setDuration(500);
        mAloudImgReadingAnimBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mAloudImgReading.setTranslationY((mTotalHeight - mUpImgFollow.getHeight() - mAloudImgReading.getHeight()) * value);
                float alpha = 1f - value - 0.4f;
                mUpImgFollow.setAlpha(alpha);
                mImgDialogue.setAlpha(alpha);
                mImgRecite.setAlpha(alpha);
                mBg.setAlpha(1f - value + 0.5f);
            }
        });
        mAloudImgReadingAnimBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mLlBox.setVisibility(INVISIBLE);
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAloudImgReading.setAlpha(1f);
                mUpImgFollow.setAlpha(1f);
                mImgDialogue.setAlpha(1f);
                mImgRecite.setAlpha(1f);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initImgDialogueAnimBack() {
        mImgDialogueAnimBack = ObjectAnimator.ofFloat(1, 0);
        mImgDialogueAnimBack.setDuration(300);
        mImgDialogueAnimBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mImgDialogue.setTranslationY((mTotalHeight - mUpImgFollow.getHeight() - mAloudImgReading.getHeight() - mImgDialogue.getHeight()) * value);
                float alpha = 1f - value - 0.4f;
                mAloudImgReading.setAlpha(alpha);
                mUpImgFollow.setAlpha(alpha);
                mImgRecite.setAlpha(alpha);
                mBg.setAlpha(1f - value + 0.5f);
            }
        });
        mImgDialogueAnimBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mLlBox.setVisibility(INVISIBLE);
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mUpImgFollow.setAlpha(1f);
                mImgDialogue.setAlpha(1f);
                mImgRecite.setAlpha(1f);
                mAloudImgReading.setAlpha(1f);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initImgReciteAnimBack() {
        mImgReciteAnimBack = ObjectAnimator.ofFloat(1, 0);
        mImgReciteAnimBack.setDuration(500);
        mImgReciteAnimBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float alpha = 1f - value - 0.4f;
                mAloudImgReading.setAlpha(alpha);
                mUpImgFollow.setAlpha(alpha);
                mImgDialogue.setAlpha(alpha);
                mBg.setAlpha(1f - value + 0.5f);
                mImgRecite.setTranslationY((mTotalHeight - mUpImgFollow.getHeight() - mAloudImgReading.getHeight() - mImgDialogue.getHeight() - mImgRecite.getHeight()) * value);
            }
        });
        mImgReciteAnimBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mLlBox.setVisibility(INVISIBLE);
                mImgDialogue.setOnClickListener(null);
                mAloudImgReading.setOnClickListener(null);
                mUpImgFollow.setOnClickListener(null);
                mImgRecite.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAloudImgReading.setAlpha(1f);
                mUpImgFollow.setAlpha(1f);
                mImgDialogue.setAlpha(1f);
                mImgRecite.setAlpha(1f);
                mImgRecite.setOnClickListener(OperationView.this);
                mAloudImgReading.setOnClickListener(OperationView.this);
                mUpImgFollow.setOnClickListener(OperationView.this);
                mImgDialogue.setOnClickListener(OperationView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void startFollowAnim() {
        mCurrentAnim = FOOLOW_UP;
        mUpImgFollowAnim.start();
    }

    public void startFollowAnimBack() {
        mCurrentAnim = DEFAULE;
        mUpImgFollow.setVisibility(VISIBLE);
        mAloudImgReading.setVisibility(VISIBLE);
        mImgDialogue.setVisibility(VISIBLE);
        mImgRecite.setVisibility(VISIBLE);
        mUpImgFollowAnimBack.start();
    }

    public void startAloudImgReadingAnim() {
        mCurrentAnim = ALOUD_READING;
        mAloudImgReadingAnim.start();
    }

    public void startAloudImgReadingAnimBack() {
        mCurrentAnim = DEFAULE;
        mUpImgFollow.setVisibility(VISIBLE);
        mAloudImgReading.setVisibility(VISIBLE);
        mImgDialogue.setVisibility(VISIBLE);
        mImgRecite.setVisibility(VISIBLE);
        mAloudImgReadingAnimBack.start();
    }

    public void startImgDialogueAnimAnim() {
        mCurrentAnim = DIALOGUE;
        mImgDialogueAnim.start();
    }


    public void startImgDialogueAnimAnimBack() {
        mCurrentAnim = DEFAULE;
        mUpImgFollow.setVisibility(VISIBLE);
        mAloudImgReading.setVisibility(VISIBLE);
        mImgDialogue.setVisibility(VISIBLE);
        mImgRecite.setVisibility(VISIBLE);
        mImgDialogueAnimBack.start();
    }

    public void startImgReciteAnim() {
        mCurrentAnim = RECITE;
        mImgReciteAnim.start();
    }

    public void startImgReciteAnimBack() {
        mCurrentAnim = DEFAULE;
        mUpImgFollow.setVisibility(VISIBLE);
        mAloudImgReading.setVisibility(VISIBLE);
        mImgDialogue.setVisibility(VISIBLE);
        mImgRecite.setVisibility(VISIBLE);
        mImgReciteAnimBack.start();
    }


    public void removeDialogueClick() {
        mImgDialogue.setOnClickListener(null);
    }

    public void addDialogueClick() {
        mImgDialogue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.follow_up_img) {
            mUpImgFollow.setImageResource(R.mipmap.ic_follow_up);
            mAloudImgReading.setImageResource(R.mipmap.ic_reading_aloud_unselect);
            mImgDialogue.setImageResource(R.mipmap.ic_dialogue_unselect);
            mImgRecite.setImageResource(R.mipmap.ic_recite_unselect);
            if (mCurrentAnim == FOOLOW_UP) {
                startFollowAnimBack();
            } else {
                startFollowAnim();
            }
        } else if (i == R.id.reading_aloud_img) {
            mUpImgFollow.setImageResource(R.mipmap.ic_follow_up_unselect);
            mAloudImgReading.setImageResource(R.mipmap.ic_reading_aloud);
            mImgDialogue.setImageResource(R.mipmap.ic_dialogue_unselect);
            mImgRecite.setImageResource(R.mipmap.ic_recite_unselect);
            if (mCurrentAnim == ALOUD_READING) {
                startAloudImgReadingAnimBack();
            } else {
                startAloudImgReadingAnim();
            }

        } else if (i == R.id.dialogue_img) {
            mUpImgFollow.setImageResource(R.mipmap.ic_follow_up_unselect);
            mAloudImgReading.setImageResource(R.mipmap.ic_reading_aloud_unselect);
            mImgDialogue.setImageResource(R.mipmap.ic_dialogue);
            mImgRecite.setImageResource(R.mipmap.ic_recite_unselect);
            if (mCurrentAnim == DIALOGUE) {
                startImgDialogueAnimAnimBack();
            } else {
                startImgDialogueAnimAnim();
            }

        } else if (i == R.id.recite_img) {
            mUpImgFollow.setImageResource(R.mipmap.ic_follow_up_unselect);
            mAloudImgReading.setImageResource(R.mipmap.ic_reading_aloud_unselect);
            mImgDialogue.setImageResource(R.mipmap.ic_dialogue_unselect);
            mImgRecite.setImageResource(R.mipmap.ic_recite);
            if (mCurrentAnim == RECITE) {
                startImgReciteAnimBack();
            } else {
                startImgReciteAnim();
            }
        }
    }

    public void reset() {
        mUpImgFollow.setImageResource(R.mipmap.ic_follow_up);
        mAloudImgReading.setImageResource(R.mipmap.ic_reading_aloud_unselect);
        mImgDialogue.setImageResource(R.mipmap.ic_dialogue_unselect);
        mImgRecite.setImageResource(R.mipmap.ic_recite_unselect);

    }

    public void visiable() {
        mUpImgFollow.setVisibility(VISIBLE);
        mAloudImgReading.setVisibility(GONE);
        mImgDialogue.setVisibility(GONE);
        mImgRecite.setVisibility(GONE);
    }
}
