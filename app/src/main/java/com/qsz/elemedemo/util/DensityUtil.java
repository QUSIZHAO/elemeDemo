package com.qsz.elemedemo.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 设备屏幕开关
 * Created by QSZ on 2018/6/20 08:48
 * @author QSZ
 */
public final class DensityUtil {

    private static int sWidth = 720;
    private static int sHeight = 1080;
    private static int[] deviceWidthHeight = new int[2];

    public static int[] getDeviceInfo(Context context) {
        if ((deviceWidthHeight[0] == 0) && (deviceWidthHeight[1] == 0)) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getMetrics(metrics);

            deviceWidthHeight[0] = metrics.widthPixels;
            deviceWidthHeight[1] = metrics.heightPixels;
        }
        return deviceWidthHeight;
    }

    /**
     * 获取屏幕宽度的方法
     */
    public static int getScreenWith(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度的方法
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        return wm.getDefaultDisplay().getHeight();
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        try {
            @SuppressLint("PrivateApi") Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * dp -> px
     * dpValue dp数值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px -> dp
     * pxValue px 的数值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
