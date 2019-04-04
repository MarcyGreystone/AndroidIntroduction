package com.marcy.androidintroduction.activitylifecycle;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.marcy.androidintroduction.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private MainFragment mFragment;
    private EditText mEt;
    private ServiceConnection mServiceConnection;
    private FirstService.TestFirstBinder mTestFirstBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEt = findViewById(R.id.et_input);

        if(savedInstanceState != null){
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG, TAG + "-->onCreate: restore extra_test" + test);
        }
        startServiceByAction();
//        bindServiceForTest();
//        startServiceForTest();
//        mFragment = new MainFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.fl_fragment , mFragment);
//        transaction.commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, TAG + "-->onCreateOptionsMenu: ");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Log.d(TAG, TAG + "-->onOptionsMenuClosed: ");
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d(TAG, TAG + "-->onSaveInstanceState: ");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, TAG + "-->onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
        outState.putString("extra_test","测试数据");
        Parcelable test  = mEt.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG, TAG + "-->onRestoreInstanceState: " + test);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_jump:
                startActivity(new Intent(this , MainActivity.class));
//                finish();
                break;
            case R.id.bt_dialog:
                showDialog();
                break;
        }
    }

    private void startServiceByAction(){
        //若不采用该方式隐式启动service，则会报Service Intent must be explicit异常。 这是Android 5.0 (Lollipop) 之后的规定
        Intent intent = new Intent();
        intent.setAction("com.example.firstservice");
        intent.setPackage(getPackageName());    //兼容Android 5.0
        startService(intent);
    }

    private void startServiceForTest(){
        startService(new Intent(this , FirstService.class));
    }

    private void bindServiceForTest(){
        mServiceConnection = new TestServiceConnection();
        bindService(new Intent(this , FirstService.class) , mServiceConnection ,   Context.BIND_AUTO_CREATE);
    }

    private void showDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("测试acitivity生命周期");
        dialog.setMessage("当前acitivity处于前台，但无法直接和用户直接交互状态");
        dialog.setPositiveButton("跳转到其他Activity", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this , SecondActivity.class));
                finish();
            }
        });
        dialog.setNegativeButton("隐藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                unbindService(mServiceConnection);
//                dialog.dismiss();
            }
        });
        dialog.show();
    }

    class TestServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, TAG + "-->onServiceConnected: ");
            mTestFirstBinder = (FirstService.TestFirstBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, TAG + "-->onServiceDisconnected: ");
        }
    }
}
