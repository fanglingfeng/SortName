package com.example.fanglingfeng.sortname;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fanglingfeng on 2017/4/6.
 */

public class IndexSideView extends View {
    private String[] mLetterIndexArray = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    };
    private List<String> mLetterIndexList;
    private OnTouchLetterListener onTouchLetterListener;
    private int currentLetterIndex = -1;
    private Paint mPaint;
    private Rect mTextBound;
    private int mViewHeight;
    private int mViewWight;

    public IndexSideView(Context context) {
        this(context, null);
    }

    public IndexSideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mLetterIndexList = Arrays.asList(mLetterIndexArray);
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(DisplayUtils.sp2px(getContext(), 12));
        setBackgroundColor(Color.alpha(0));
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewHeight = h;
        mViewWight = w;
    }

    @Override protected void onDraw(Canvas canvas) {
        int size = mLetterIndexList.size();
        float cellHeight = mViewHeight * 1.0f / size;
        for (int index = 0; index < mLetterIndexList.size(); index++) {
            mPaint.setColor(Color.BLACK);
            if (index == currentLetterIndex) {
                mPaint.setColor(Color.WHITE);
            }
            String letter = mLetterIndexList.get(index);
            float xPos = (mViewWight - mPaint.measureText(letter)) / 2;
            mPaint.getTextBounds(letter, 0, letter.length(), mTextBound);
            float textHeight = mTextBound.width();
            float yPos = textHeight / 2 + cellHeight / 2 + cellHeight * index;
            canvas.drawText(letter, xPos, yPos, mPaint);
        }
    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        float y = event.getY();
        int size = mLetterIndexList.size();
        int oldIndex = currentLetterIndex;
        int tempIndex = (int) (y / mViewHeight * size);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            setBackgroundColor(Color.alpha(0));
            currentLetterIndex = -1;
            invalidate();
            if (onTouchLetterListener != null) {
                onTouchLetterListener.onTouchedLetter();
            }
        } else {
            setBackgroundResource(R.drawable.bg_index_slide_bar);
            if (tempIndex != oldIndex && tempIndex < size&&tempIndex>=0) {
                currentLetterIndex = tempIndex;
                if (onTouchLetterListener != null) {
                    onTouchLetterListener.onTouchingLetter(mLetterIndexList.get(tempIndex));
                }
                invalidate();
            }
        }
        return true;
    }
    public interface OnTouchLetterListener{
        void onTouchedLetter();
        void onTouchingLetter(String letter);
    }
    public void setOnTouchLetterListener(OnTouchLetterListener onTouchLetterListener){
        this.onTouchLetterListener = onTouchLetterListener;
    }
    public void setLetterList(List<String> list){
        setLetterList(list,true);
    }

    private void setLetterList(List<String> list, boolean b) {
        this.mLetterIndexList = b?list:Arrays.asList(mLetterIndexArray);
        invalidate();
    }
}
