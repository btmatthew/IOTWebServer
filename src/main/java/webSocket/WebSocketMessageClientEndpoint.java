package webSocket;

import webSocket.objects.Message;

import java.net.URI;
import java.util.HashMap;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * ChatServer Client
 *
 * @author Jiji_Sasidharan
 */
@ClientEndpoint
public class WebSocketMessageClientEndpoint {

    private Session socketSession = null;
    private HashMap<String,MessageHandler> messageHandlerHashMap = new HashMap<>();


    WebSocketMessageClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the socketSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.socketSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the socketSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.socketSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        Message messageObject = new Message().decode(message);
        messageHandlerHashMap.get(messageObject.getHandlerID()).handleMessage(messageObject);
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(String handleID,MessageHandler msgHandler) {
        messageHandlerHashMap.put(handleID,msgHandler);
    }

    public void removeMessageHandler(String hanlerID){
        messageHandlerHashMap.remove(hanlerID);
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.socketSession.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public interface MessageHandler {

        void handleMessage(Message message);
    }
}