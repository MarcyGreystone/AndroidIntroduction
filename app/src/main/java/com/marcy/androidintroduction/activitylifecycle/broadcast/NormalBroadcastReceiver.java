package com.marcy.androidintroduction.activitylifecycle.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Marcy on 2019/4/17
 */
public class NormalBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, TAG + "-->onReceive: " + intent.getAction());
        if(intent.getAction().equals("Haurchefant")){
            Toast.makeText(context, "Haurchefant", Toast.LENGTH_SHORT).show();
        }
    }
}
