package com.marcy.androidintroduction.activitylifecycle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Marcy on 2019/4/17
 */
public class OrderedSecondBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        int getBroadData = intent.getIntExtra("Key" , 0);
        Log.d(TAG, TAG + "-->onReceive: result=" + getBroadData);

    }
}
