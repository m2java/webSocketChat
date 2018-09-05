package servlets.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@ServerEndpoint("/chat")
public class WebServerEndpoint {
    static Set<Session> chatRoomUsers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void handleOpen(Session userSession) {
        chatRoomUsers.add(userSession);
    }

    @OnMessage
    public void handleMessage(String message) throws IOException {
        Iterator<Session> iterator = chatRoomUsers.iterator();
        while (iterator.hasNext()) {
            iterator.next().getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void handleClose(Session userSession) {
        chatRoomUsers.remove(userSession);
    }


}
