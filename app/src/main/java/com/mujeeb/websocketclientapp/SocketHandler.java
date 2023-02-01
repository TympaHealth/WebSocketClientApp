package com.mujeeb.websocketclientapp;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


class SocketHandler extends Handler {
    private final WeakReference<MainActivity> mActivity;

    public SocketHandler(MainActivity activity) {
        mActivity = new WeakReference<MainActivity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        MainActivity activity = mActivity.get();
        if (activity != null) {
            try {
                activity.message = (String) msg.obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    }
}