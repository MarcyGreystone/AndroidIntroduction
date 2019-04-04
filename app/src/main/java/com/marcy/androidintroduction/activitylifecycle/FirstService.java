package com.marcy.androidintroduction.activitylifecycle;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by Marcy on 2019/4/3
 */
public class FirstService extends Service{
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + "-->onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, TAG + "-->onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, TAG + "-->onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, TAG + "-->onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, TAG + "-->onDestroy: ");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, TAG + "-->onBind: ");
        return null;
    }

    public interface ITestFirstBinder{
        void invokeTestFirstService();
    }

    public class TestFirstBinder extends Binder implements ITestFirstBinder{
        public void stopService(ServiceConnection serviceConnection){
            unbindService(serviceConnection);
        }

        @Override
        public void invokeTestFirstService() {
            for(int i = 0 ; i < 20 ; i++){
                Log.d(TAG, TAG + "-->invokeTestFirstService: " + i);
            }
        }
    }
}
