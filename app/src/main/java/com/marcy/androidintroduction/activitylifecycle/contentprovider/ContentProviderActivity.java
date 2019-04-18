package com.marcy.androidintroduction.activitylifecycle.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.marcy.androidintroduction.R;
import com.marcy.androidintroduction.activitylifecycle.broadcast.NormalBroadcastReceiver;

/**
 * Created by Marcy on 2019/4/17
 */
public class ContentProviderActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        operateUser();
        operateJob();
    }

    private void operateUser(){
        setUri();
        insertData();
        getContentResolverAndInsertDataAndQueryData();
    }

    private void operateJob(){
        setJobUri();
        insertJobData();
        getJobContentResolverAndInsertDataAndQueryData();
    }


    private Uri setUri(){
        Uri uri_user = Uri.parse("content://com.marcy.sampleprovider/user");
        return uri_user;
    }
    private ContentValues insertData(){
        ContentValues values = new ContentValues();
        values.put("_id" , 3);
        values.put("name" , "haurchefant");
        return values;
    }

    private void getContentResolverAndInsertDataAndQueryData(){
        ContentResolver resolver = getContentResolver();
        resolver.insert(setUri() , insertData());
        Cursor cursor = resolver.query(setUri() , new String[]{"_id" , "name"} , null , null , null);
        while (cursor.moveToNext()){
            Log.d(TAG, TAG + "-->getContentResolverAndInsertDataAndQueryData: " + "query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
        }
        cursor.close();
    }

    private Uri setJobUri(){
        return Uri.parse("content://com.marcy.sampleprovider/job");
    }

    private ContentValues insertJobData(){
        ContentValues values = new ContentValues();
        values.put("_id" , 3);
        values.put("job" , "knight");
        return values;
    }

    private void getJobContentResolverAndInsertDataAndQueryData(){
        ContentResolver resolver = getContentResolver();
        resolver.insert(setJobUri() , insertJobData());
        Cursor cursor = resolver.query(setJobUri() , new String[]{"_id" , "job"} , null , null , null);
        while (cursor.moveToNext()){
            Log.d(TAG, TAG + "-->getJobContentResolverAndInsertDataAndQueryData: " + "query job:" + cursor.getInt(0) + " " + cursor.getString(1));
        }
        cursor.close();
    }
}
