package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lzr.com.learn_lib.R;


public class RobotHeader extends LinearLayout {

    ImageView mCenterRobot;
    ImageView mLeftYellowFG;
    ImageView mRightWhiteBG;
    public static final int ANIMATION_CIRCLE = 300;
    TranslateAnimation mToRightAnim;
    TranslateAnimation mToLeftAnim;
    boolean stoped;

    public RobotHeader(Context context) {
        this(context,null);
    }

    public RobotHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.robot_refresh_header,null,false);
        this.addView(view);
        setGravity(Gravity.CENTER);
        mCenterRobot = (ImageView) view.findViewById(R.id.center_robot);
        mLeftYellowFG = (ImageView) view.findViewById(R.id.left_yellow_point_fg);
        mRightWhiteBG = (ImageView) view.findViewById(R.id.right_white_point_bg);
        int xTrans = dip2px(getContext(), 55);
        mToRightAnim = new TranslateAnimation(0, xTrans, 0, 0);
        mToRightAnim.setRepeatCount(Animation.INFINITE);
        mToRightAnim.setRepeatMode(Animation.REVERSE);
        mToRightAnim.setDuration(ANIMATION_CIRCLE);

        mToLeftAnim = new TranslateAnimation(0, -xTrans, 0, 0);
        mToLeftAnim.setRepeatCount(Animation.INFINITE);
        mToLeftAnim.setRepeatMode(Animation.REVERSE);
        mToLeftAnim.setDuration(ANIMATION_CIRCLE);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void startAnimation() {
        stoped = false;

        mLeftYellowFG.startAnimation(mToRightAnim);
        mRightWhiteBG.startAnimation(mToLeftAnim);

    }

    public void clearAllAnimation() {
        stoped = true;
        if (mLeftYellowFG != null) {

            mLeftYellowFG.clearAnimation();
            mLeftYellowFG.setTranslationX(0);

            mRightWhiteBG.clearAnimation();
            mRightWhiteBG.setTranslationX(0);

        }
    }

}
