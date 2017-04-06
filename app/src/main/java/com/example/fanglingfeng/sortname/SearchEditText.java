package com.example.fanglingfeng.sortname;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fanglingfeng on 2017/4/6.
 */

public class SearchEditText extends android.support.v7.widget.AppCompatEditText {
    private Drawable searchDrawable;
    private Drawable deleteDrawable;
    public SearchEditText(Context context) {
        this(context,null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        searchDrawable = getCompoundDrawables()[0];
        if (searchDrawable == null) {
            searchDrawable = ContextCompat.getDrawable(getContext(),R.mipmap.search);
        }
        int intrinsicWidth = searchDrawable.getIntrinsicWidth();
        int intrinsicHeight = searchDrawable.getIntrinsicHeight();
        int width = (int) (intrinsicWidth*0.8f);
        int height = (int) (intrinsicHeight*0.8f);
        searchDrawable.setBounds(0,0,width,height);
        deleteDrawable = getCompoundDrawables()[2];
        if (deleteDrawable != null) {
            deleteDrawable = ContextCompat.getDrawable(getContext(),R.mipmap.delete);

        }
        intrinsicWidth = deleteDrawable.getIntrinsicWidth();
        intrinsicHeight = deleteDrawable.getIntrinsicHeight();
        width = (int) (intrinsicWidth*0.8f);
        height = (int) (intrinsicHeight*0.8f);
        deleteDrawable.setBounds(0,0,width,height);
        setDeleteDrawable(true);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setDeleteDrawable(charSequence.length()>0);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
               boolean visible = hasFocus()&&getText().length()>0;
                setDeleteDrawable(visible);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (deleteDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int left = getWidth()-getPaddingRight()-deleteDrawable.getIntrinsicWidth();
            int right = getWidth()-getPaddingRight();
            if (getX() >= left && getX() <= right) {
                this.setText("");
            }


        }


        return super.onTouchEvent(event);
    }

    private void setDeleteDrawable(boolean visible) {
        Drawable right = visible?deleteDrawable:null;
        setCompoundDrawables(searchDrawable,null,right,null);
    }
}
