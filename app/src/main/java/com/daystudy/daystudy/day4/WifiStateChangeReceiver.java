package com.daystudy.daystudy.day4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/5/16
 */

public class WifiStateChangeReceiver extends BroadcastReceiver {
    private TextView mView;

    public WifiStateChangeReceiver(TextView tv) {
        mView = tv;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetWorkUtils.NetWorkType type = NetWorkUtils.getNetworkType(context);
            Toast.makeText(context, "type:" + type.value, Toast.LENGTH_SHORT).show();
        }else if ("haaha".equals(action)) {
            mView.setText("haha");
        }

    }
}
