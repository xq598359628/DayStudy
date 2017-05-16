package com.daystudy.daystudy.day4;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.daystudy.daystudy.R;

/**
 * 监听网络状态
 *
 *
 */
public class NetWorkStateActivity extends AppCompatActivity {

    private WifiStateChangeReceiver mReceiver;
    public static final String action = "haaha";
    private LocalBroadcastManager mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(action);

        mReceiver = new WifiStateChangeReceiver((TextView) findViewById(R.id.tv));
        //mInstance = LocalBroadcastManager.getInstance(this);
      //  mInstance.registerReceiver(mReceiver,filter);
        registerReceiver(mReceiver,filter);
    }

    public void send(View view){
     //   mInstance.sendBroadcast(new Intent(action));
        sendBroadcast(new Intent(action));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mInstance.unregisterReceiver(mReceiver);
        unregisterReceiver(mReceiver);
    }
}
