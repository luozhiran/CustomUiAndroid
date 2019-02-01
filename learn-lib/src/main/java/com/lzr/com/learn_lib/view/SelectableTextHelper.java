package com.lzr.com.learn_lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.data.SelectionInfo;
import com.lzr.com.learn_lib.interfaces.OnSelectListener;
import com.lzr.com.utils_lib.utils.DpPxUtils;
import com.lzr.com.utils_lib.utils.TextLayoutUtils;

import java.util.PrimitiveIterator;

public class SelectableTextHelper {
    private final static int DEFAULT_SELECTION_LENGTH = 1;

    private Builder mBuilder;
    private TextView mTextView;
    private int mSelectedColor;
    private int mCursorHandleColor;
    private int mCursorHandleSize;
    private SelectionInfo mSelectionInfo;
    private int mTouchX = 0;
    private int mTouchY = 0;
    private ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    private Spannable mSpannable;
    private BackgroundColorSpan mSpan;
    private OnSelectListener mSelectListener;

    public SelectableTextHelper(Builder builder) {
        mSelectionInfo = new SelectionInfo();
        mTextView = builder.mTextView;
        mSelectedColor = builder.mSelectedColor;
        mCursorHandleColor = builder.mCursorHandleColor;
        mCursorHandleSize = DpPxUtils.dp2px(builder.mCursorHandleSizeInDp);
        init();
    }

    private void init() {
        mTextView.setText(mTextView.getText(), TextView.BufferType.SPANNABLE);
        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSelectView(mTouchX, mTouchY);
                return true;
            }
        });

        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                return false;
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectView(mTouchX, mTouchY);
            }
        });

        mTextView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
        mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                return true;
            }
        };

        mTextView.getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);

    }


    private void resetSelectInfo() {
        mSelectionInfo.mSelectionContent = null;
        if (mSpannable != null && mSpan != null) {
            mSpannable.removeSpan(mSpan);
            mSpan = null;
        }
    }

    private void showSelectView(int x, int y) {
        resetSelectInfo();
        int startOffset = TextLayoutUtils.getPreciseOffset(mTextView, x, y);
        int endOffset = startOffset + DEFAULT_SELECTION_LENGTH;
        if (mTextView.getText() instanceof Spannable) {
            mSpannable = (Spannable) mTextView.getText();
        }
        if (mSpannable == null || startOffset > mTextView.getText().length()) {
            return;
        }

        selectText(startOffset, endOffset);
    }


    private void selectText(int startPos, int endPos) {
        if (startPos != -1) {
            mSelectionInfo.mStart = startPos;
        }
        if (endPos != -1) {
            mSelectionInfo.mEnd = endPos;
        }
        if (mSelectionInfo.mStart > mSelectionInfo.mEnd) {
            int temp = mSelectionInfo.mStart;
            mSelectionInfo.mStart = mSelectionInfo.mEnd;
            mSelectionInfo.mEnd = temp;
        }

        if (mSpannable != null) {
            if (mSpan == null) {
                mSpan = new BackgroundColorSpan(mSelectedColor);
            }
        }
        mSelectionInfo.mSelectionContent = mSpannable.subSequence(mSelectionInfo.mStart, mSelectionInfo.mEnd).toString();
        mSpannable.setSpan(mSpan, mSelectionInfo.mStart, mSelectionInfo.mEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        if (mSelectListener != null) {
            mSelectListener.onTextSelected(mSelectionInfo.mSelectionContent);
        }
    }


    private class OperateWindow {
        private PopupWindow mPopupWindow;
        /**
         * Computes the coordinates of this view on the screen.
         * The Argument must be an array of two integers. After the method returns,
         * the array contains the x and y location int that order
         * use method is {#getLocationOnScreen(mTempCoors)}
         */
        private int[] mTempCoors = new int[2];

        // width of PopWindow
        private int mWidth;
        // height of PopWindow
        private int mHeight;

        @SuppressLint("Range")
        public OperateWindow(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.pop_window_view, null, false);
            view.measure(View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.MATCH_PARENT, View.MeasureSpec.UNSPECIFIED)
                    , View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.MATCH_PARENT, View.MeasureSpec.UNSPECIFIED));
            mWidth = view.getMeasuredWidth();
            mHeight = view.getMeasuredHeight();
            mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
            mPopupWindow.setClippingEnabled(false);
            view.findViewById(R.id.tv_copy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            view.findViewById(R.id.tv_select_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void show() {
            mTextView.getLocationOnScreen(mTempCoors);
            Layout layout = mTextView.getLayout();

        }
    }

    public void setSelectListener(OnSelectListener selectListener) {
        this.mSelectListener = selectListener;
    }

    public static class Builder {
        private TextView mTextView;
        private int mCursorHandleColor = 0xFF1379D6;
        private int mSelectedColor = 0xFFAFE1F4;
        private float mCursorHandleSizeInDp = 24;

        public Builder(TextView view) {
            this.mTextView = view;
        }

        public Builder setCursorHandleColor(@ColorInt int cursorHandleColor) {
            mCursorHandleColor = cursorHandleColor;
            return this;
        }

        public Builder setCursorHandleSizeInDp(float cursorHandleSizeInDp) {
            mCursorHandleSizeInDp = cursorHandleSizeInDp;
            return this;
        }

        public Builder setSelectedColor(@ColorInt int selectedBgColor) {
            mSelectedColor = selectedBgColor;
            return this;
        }

        public SelectableTextHelper build() {
            return new SelectableTextHelper(this);
        }
    }
}
