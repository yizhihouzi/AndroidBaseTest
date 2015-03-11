package com.example.bigbigboy.androidbasetest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class ActivityLifecycleTest02 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lifecycle_test02);
        Log.d("LifeCycleTest", "LifeCycleTest------>onCreate02");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycleTest", "LifeCycleTest------>onStart02");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycleTest", "LifeCycleTest------>onResume02");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycleTest", "LifeCycleTest------>onPause02");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycleTest", "LifeCycleTest------>onStop02");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycleTest", "LifeCycleTest------>onDestroy02");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("LifeCycleTest", "LifeCycleTest------>onSaveInstanceState02");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LifeCycleTest", "LifeCycleTest------>onRestoreInstanceState02");
    }
}
