package com.example.fanglingfeng.sortname;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by fanglingfeng on 2017/4/6.
 */

public class DisplayUtils {
    public static float dp2px(Context context, int dip) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm);
    }

    public static float sp2px(Context context, int sp){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, dm);
    }
}
