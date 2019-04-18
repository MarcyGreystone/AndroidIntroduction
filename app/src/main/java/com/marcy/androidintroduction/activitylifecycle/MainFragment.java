package com.marcy.androidintroduction.activitylifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.marcy.androidintroduction.R;

/**
 * Created by Marcy on 2019/3/18
 */
public class MainFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {

        Log.d(TAG, TAG + "-->onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_main , container , false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, TAG + "-->onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, TAG + "-->onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, TAG + "-->onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, TAG + "-->onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, TAG + "-->onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, TAG + "-->onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, TAG + "-->onDetach: ");
        super.onDetach();
    }
}
