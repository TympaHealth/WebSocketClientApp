package com.mujeeb.websocketclientapp;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.net.URI;

import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class SocketClient extends WebSocketClient {


    private static String TAG = SocketClient.class.getName();
    private Handler mHandler;

    public SocketClient(URI serverURI, Handler handler) {
        super(serverURI);
        this.mHandler = handler;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mujeeb :)");
        Log.i(TAG, "new connection opened");
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG,"received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG,"closed with exit code " + code + " additional info: " + reason);
    }



    @Override
    public void onMessage(ByteBuffer message) {

        Log.i(TAG,"received ByteBuffer");

        byte[] buffer = message.array();

        Message m = mHandler.obtainMessage();
        m.obj = new String(buffer);
        mHandler.sendMessage(m);
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG, "an error occurred:" + ex);
    }


}