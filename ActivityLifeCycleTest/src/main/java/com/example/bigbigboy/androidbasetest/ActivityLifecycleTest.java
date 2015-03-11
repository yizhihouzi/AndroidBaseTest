package com.example.bigbigboy.androidbasetest;

import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ActivityLifecycleTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lifecycle_test);
        Log.d("LifeCycleTest", "LifeCycleTest------>onCreate01");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycleTest", "LifeCycleTest------>onStart01");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycleTest", "LifeCycleTest------>onResume01");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycleTest", "LifeCycleTest------>onPause01");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycleTest", "LifeCycleTest------>onStop01");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycleTest", "LifeCycleTest------>onDestroy01");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("LifeCycleTest", "LifeCycleTest------>onSaveInstanceState01");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LifeCycleTest", "LifeCycleTest------>onRestoreInstanceState01");
    }

    public void onClick(View button) {
        startActivity(new Intent(ActivityLifecycleTest.this, ActivityLifecycleTest02.class));
    }
}
