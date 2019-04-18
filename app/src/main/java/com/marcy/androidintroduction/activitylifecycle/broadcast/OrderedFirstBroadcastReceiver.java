package com.marcy.androidintroduction.activitylifecycle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Marcy on 2019/4/17
 */
public class OrderedFirstBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, TAG + "-->onReceive: " + intent.getAction());
        int getBroadData = intent.getIntExtra("Key" , 0);
        if(intent.getAction().equals("Ordered")){
            Log.d(TAG, TAG + "-->onReceive: result=" + getBroadData);
        }
    }
}
