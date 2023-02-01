package com.mujeeb.websocketclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    public String message;

    private final Handler handler = new SocketHandler(this);


    private Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (message!=null){
                            mTextView.setText(message);
                        }
                    }
                });
            } finally {
                handler.postDelayed(mStatusChecker, 1000/15);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        try {
            WebSocketClient client = new SocketClient(new URI("ws://192.168.1.153:8080"), handler);
            client.connect();
        }catch (URISyntaxException ex){
            ex.printStackTrace();
        }

        mStatusChecker.run();
    }
}