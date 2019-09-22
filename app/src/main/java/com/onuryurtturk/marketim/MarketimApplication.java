package com.onuryurtturk.marketim;

import android.app.Application;
import android.os.SystemClock;

public class MarketimApplication extends Application {

    /**
     * Application class, SystemClock.sleep() for splash screen process
     */

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}