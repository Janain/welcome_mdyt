package com.wangjj.app.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
/**
 * @Author Wangjj
 * @Create 2018/5/2  15:52.
 * @Title 隐藏软键盘
 */
public class InPutTypeUtil {
    /**
     * 隐藏软键盘
     *
     * @param context 上下文
     */
    public static void hidenputKeyboardImmediately(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
