package com.work.auth.webSocket;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.work.auth.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 处理机制
 * @author ZhaiYunpeng
 */
@Component
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {

    /**
     * 在线用户列表
     */
    private static final Map<String, WebSocketSession> USERS;

    static {
        USERS = new HashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connect websocket successful!");
        String url = StringUtil.getString(session.getUri());
        String id = url.split("ID=")[1];
        log.info(id);
        if (id != null) {
            USERS.put(id, session);
            session.sendMessage(new TextMessage("{\"message\":\"socket successful connection!\"}"));
            log.info("id:" + id + ",session:" + session + "");
        }
        log.info("current user number is:" + USERS.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            JSONObject jsonobject = JSONObject.parseObject(StringUtil.getString(message.getPayload()));
            log.info("receive message:" + jsonobject);
            String msg = StringUtil.getString(jsonobject.get("msg"));
            log.info("来自" + (String) session.getAttributes().get("WEBSOCKET_USER_ID") + "的消息[" + msg + "]");
            Object id = jsonobject.get("id");
            sendMessageToUser(StringUtil.getString(id), new TextMessage("{\"msg\":\"" + msg + "\"}"));
        } catch (Exception e) {
            log.error("e", e);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.error("connect error", exception);
        USERS.remove(getClientId(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.error("connection closed: " + closeStatus);
        USERS.remove(getClientId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 发送信息给指定用户
     *
     * @param clientId 客户端ID
     * @param message  信息
     * @return boolean
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (USERS.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = USERS.get(clientId);
        log.info("sendMessage:" + message);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            log.error("e", e);
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     *
     * @param message TextMessage JSON 格式字符串
     * @return boolean
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = USERS.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = USERS.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                log.error("e", e);
                allSendSuccess = false;
            }
        }
        return allSendSuccess;
    }

    /**
     * 获取用户标识
     *
     * @param session WebSocketSession
     * @return String
     */
    private String getClientId(WebSocketSession session) {
        try {
            return (String) session.getAttributes().get("WEBSOCKET_USER_ID");
        } catch (Exception e) {
            log.error("e", e);
            return null;
        }
    }

    /**
     * 获取在线人数
     *
     * @return int
     */
    public synchronized int getOnlineNum() {
        return USERS.size();
    }
}

