package com.example.gome.mybroadcastparctics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public static final String COM_EXAMPLE_FORCE_OFFLINE = "com.example.FORCE_OFFLINE";
    private LocalBroadcastManager mLocalBroadcastManager;
    private BaseReceiver mBaseReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(BaseActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(COM_EXAMPLE_FORCE_OFFLINE);
        //mLocalBroadcastManager  = LocalBroadcastManager.getInstance(this);
        mBaseReceiver = new BaseReceiver();
        //mLocalBroadcastManager.registerReceiver(mBaseReceiver, mIntentFilter);
        registerReceiver(mBaseReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mBaseReceiver != null){
            //mLocalBroadcastManager.unregisterReceiver(mBaseReceiver);
            unregisterReceiver(mBaseReceiver);
        }
        mBaseReceiver = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(BaseActivity.this);
    }

    class BaseReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, final Intent intent) {
            if (intent.getAction().equals(COM_EXAMPLE_FORCE_OFFLINE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("FBI Warning !");
                builder.setMessage("You are forced to be offline, Please try to login again !");
                builder.setCancelable(false);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityController.finishAll();
                        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        }
    }
}
