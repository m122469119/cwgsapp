package com.isoftstone.finance.cwgsapp.util;

import android.content.Context;
import android.widget.Toast;

import com.isoftstone.finance.cwgsapp.manage.CwgsApplication;

public class ToastUtils {
    public static long LAST_CLOCK_TIME;

    public static boolean isFastClick() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long lastClockTime = LAST_CLOCK_TIME;
            if (currentTimeMillis - lastClockTime < 3000) {
                LAST_CLOCK_TIME = currentTimeMillis;
                return true;
            }
            return false;
        } finally {
        }
    }

    public static void toastResponse(String paramString) {
        toastShort(paramString);
    }

    public static void toastShort(int paramInt) {
        Toast.makeText(CwgsApplication.getAppContext(), paramInt, Toast.LENGTH_SHORT).show();
    }

    public static void toastShort(int paramInt, Object[] paramArrayOfObject) {
        Context context = CwgsApplication.getAppContext();
        if (paramArrayOfObject != null) {
            Toast.makeText(context, context.getString(paramInt, paramArrayOfObject), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(context, paramInt, Toast.LENGTH_SHORT).show();
    }

    public static void toastShort(String paramString) {
        Toast.makeText(CwgsApplication.getAppContext(), paramString, Toast.LENGTH_SHORT).show();
    }
}