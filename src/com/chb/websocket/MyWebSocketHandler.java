package com.chb.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chb.constant.ResultCode;
import com.chb.entity.ResultMessage;
import com.chb.utils.JsonUtil;

@Component
public class MyWebSocketHandler implements WebSocketHandler {
	private static final ArrayList<WebSocketSession> users;  
	   
    static {  
        users = new ArrayList<>();  
    }  
   
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        users.add(session);  
        session.sendMessage(new TextMessage(
        		JsonUtil.objectToJson(new ResultMessage(true, ResultCode.SUCCESS, "连接成功", null))));
    }  
   
    @Override  
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {  
   
        //sendMessageToUsers();  
    }  
   
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        if(session.isOpen()){  
            session.close();  
        }  
        users.remove(session);  
    }  
   
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {  
        users.remove(session);  
    }  
   
    @Override  
    public boolean supportsPartialMessages() {  
        return false;  
    }  
   
    /** 
     * 给所有在线用户发送消息 
     * 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
   
    /** 
     * 给某个用户发送消息 
     * 
     * @param userName 
     * @param message 
     */  
    public void sendMessageToUser(String businessmanId, TextMessage message) {  
        for (WebSocketSession user : users) {  
            if (user.getAttributes().get("businessmanId").equals(businessmanId)) {  
                try {  
                    if (user.isOpen()) {  
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    } 

}
