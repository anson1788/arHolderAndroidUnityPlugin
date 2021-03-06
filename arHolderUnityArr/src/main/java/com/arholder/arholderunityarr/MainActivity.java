package com.arholder.arholderunityarr;

import android.os.Bundle;
import android.widget.Toast;

import com.arholder.arholderunityarr.socketHelper.cWebSocketHelper;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"from android",
                Toast.LENGTH_SHORT).show();
        startSocketConnection();
    }

    public cWebSocketHelper _socketClient;
    public void startSocketConnection(){
        _socketClient = new cWebSocketHelper();
        _socketClient.connectSocket();
    }
}