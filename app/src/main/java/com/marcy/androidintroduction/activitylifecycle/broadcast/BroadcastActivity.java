package com.marcy.androidintroduction.activitylifecycle.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.marcy.androidintroduction.R;
import com.marcy.androidintroduction.activitylifecycle.broadcast.NormalBroadcastReceiver;

/**
 * Created by Marcy on 2019/4/17
 */
public class BroadcastActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
//        initReceiver();
//        startBroadcast();
        startOrderBroadcast();
    }
    private void initReceiver(){
        IntentFilter filter = new IntentFilter("Haurchefant");
        registerReceiver(mNormalBroadcastReceiver, filter, null, null);
    }

    private void registerReceiver(){
        registerReceiver(new NormalBroadcastReceiver() , new IntentFilter("Haurchefant"));
    }

    private void startBroadcast(){
        Intent intent = new Intent("Haurchefant");
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        sendBroadcast(intent);
    }
    private void startOrderBroadcast(){
        Intent intent = new Intent("Ordered");
        intent.putExtra("Key" , 123);
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        sendOrderedBroadcast(intent , null);
    }

    private NormalBroadcastReceiver mNormalBroadcastReceiver = new NormalBroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }
    };
}
