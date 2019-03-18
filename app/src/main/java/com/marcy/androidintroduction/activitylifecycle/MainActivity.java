package com.marcy.androidintroduction.activitylifecycle;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.FrameLayout;

import com.marcy.androidintroduction.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private MainFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onRestoreInstanceState: ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_jump:
                startActivity(new Intent(this , SecondActivity.class));
//                finish();
                break;
            case R.id.bt_dialog:
                showDialog();
                break;
        }
    }

    private void showDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("测试acitivity生命周期");
        dialog.setMessage("当前acitivity处于前台，但无法直接和用户直接交互状态");
        dialog.setPositiveButton("跳转到其他Activity", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this , SecondActivity.class));
            }
        });
        dialog.setNegativeButton("隐藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
