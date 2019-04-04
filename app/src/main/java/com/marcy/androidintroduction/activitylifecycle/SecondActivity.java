package com.marcy.androidintroduction.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.marcy.androidintroduction.R;

/**
 * Created by Marcy on 2019/3/18
 */
public class SecondActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, TAG + "-->onCreate: ");
    }


    @Override
    protected void onStart() {
        Log.d(TAG, TAG + "-->onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, TAG + "-->onResume: ");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, TAG + "-->onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, TAG + "-->onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, TAG + "-->onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, TAG + "-->onDestroy: ");
        super.onDestroy();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_jump:
//                getApplicationContext().startActivity(new Intent(getApplicationContext() , ThirdActivity.class));
                startActivity(new Intent(this , ThirdActivity.class));
//                finish();
                break;
        }
    }
}
