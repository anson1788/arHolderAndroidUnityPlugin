package com.arholder.arholderunityarr.socketHelper;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketState;
import com.unity3d.player.UnityPlayer;

import java.util.List;
import java.util.Map;

public class cWebSocketHelper {


    public  WebSocket _ws = null;
    public String _webSocketUrl = "wss://fmt0duuywk.execute-api.us-east-1.amazonaws.com/uat";
    public  void connectSocket()
    {
        try {
            WebSocketFactory factory = new WebSocketFactory();
            factory.setVerifyHostname(false);
            WebSocket ws = factory.createSocket(_webSocketUrl);
            _ws = ws;
            ws.addListener(new WebSocketAdapter(){
                @Override
                public void onTextMessage(WebSocket websocket, String text) throws Exception {
                    super.onTextMessage(websocket, text);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "Text msg string");
                    int x= text.indexOf("{");
                    String packedData = text.substring(x);
                    UnityPlayer.UnitySendMessage("Main Camera", "onWebSocketReceiveData", packedData);
                }

                @Override
                public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
                    super.onBinaryMessage(websocket, binary);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "Text msg binary");
                }

                @Override
                public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {
                    super.onTextMessage(websocket, data);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "Text msg data");
                }

                @Override
                public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
                    super.onConnectError(websocket, exception);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "Message to send");
                }

                @Override
                public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                    super.onConnected(websocket, headers);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "websocket success");
                    UnityPlayer.UnitySendMessage("Main Camera", "onWebSocketConnected", "websocket success");
                }

                @Override
                public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
                    super.onError(websocket, cause);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "websocket error");
                }

                @Override
                public void onStateChanged(WebSocket websocket, WebSocketState newState) throws Exception {
                    super.onStateChanged(websocket, newState);
                    UnityPlayer.UnitySendMessage("Main Camera", "JavaMessage", "Message to send");
                }
            });

            ws.connectAsynchronously();
        }catch (Exception e){
            e.printStackTrace();
        }
    }






    public  void socketSendMsg(String msg){
        _ws.sendText(msg);
    }

    public static void printLogForUnity(String unitymsg){
        Log.i("log for unity "," " + unitymsg);
    }


}
