package com.example.demo.messagesend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/MyWebsocket/{role}")
public class MyWebSocket {

    private static ConcurrentHashMap<UUID,MyWebSocket> websockets = new ConcurrentHashMap<>();

    private UUID uuid;
    private Session session;
    private String role;

    public String getRole() {
        return role;
    }

    private static final Logger logger = LoggerFactory.getLogger("MyWebSocket");

    @OnOpen
    public void OnOpen(Session session,@PathParam("role") String role){
        System.out.println("a");
        uuid = UUID.randomUUID();
        this.session = session;
        this.role = role;
        websockets.put(uuid,this);
        logger.info("新的客户端"+uuid+"链接，目前连接数量："+websockets.size());
    }

    /*
    * @Param message
    * message 分两部分 type 和 data
    * type = 0 则为url链接
    * type = 1 则为url测试数据
    * */

    @OnMessage
    public void onMessage(String message){
        Iterator<UUID> websocketsiterator = websockets.keySet().iterator();
        UUID tempuid = null;
        MyWebSocket myWebSocket = null;
        while(websocketsiterator.hasNext()) {
            tempuid = websocketsiterator.next();
            myWebSocket = websockets.get(tempuid);
            if (!"".equals(myWebSocket)) {
                myWebSocket.sendMessage(message);
            }
        }
    }

    @OnClose
    public void OnClose(){
            websockets.remove(this);
    }

    @OnError
    public void OnError(Session session,Throwable error){
        System.out.println("error");
        logger.info(this.uuid + "连接错误" + error);
        websockets.remove(this);
    }


    public void sendMessage(String message)  {
        try{
            this.session.getBasicRemote().sendText(message);
        } catch (IOException ioException){
            ioException.printStackTrace();
        } finally {
            logger.info("send:\t"+message);
        }
    }

    public void sendToAllPhone(String message) {
        Iterator<UUID> websocketsiterator = websockets.keySet().iterator();
        UUID tempuid = null;
        MyWebSocket myWebSocket = null;
        logger.info("send to all phone!");
        while(websocketsiterator.hasNext()) {
            tempuid = websocketsiterator.next();
            myWebSocket = websockets.get(tempuid);
            if (!"".equals(myWebSocket) && myWebSocket.getRole().equals("PHONE")) {
                myWebSocket.sendMessage(message);
            }
        }
    }

    public void sendToAllWeb(String message) {
        Iterator<UUID> websocketsiterator = websockets.keySet().iterator();
        UUID tempuid = null;
        MyWebSocket myWebSocket = null;
        logger.info("send to all html!");
        while(websocketsiterator.hasNext()) {
            tempuid = websocketsiterator.next();
            myWebSocket = websockets.get(tempuid);
            if (!"".equals(myWebSocket) && myWebSocket.getRole().equals("WEBH5")) {
                myWebSocket.sendMessage(message);
            }
        }
    }
}
