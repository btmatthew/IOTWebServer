package iotpackage.WebSockets;

import webSocket.WebSocketMessageClientEndpoint;
import webSocket.objects.Message;

public class Remote {
    private String action;
    private String from;
    private String to;
    private String userName;
    private String token;
    private int fanOption;
    private WebSocketMessageClientEndpoint webSocket;

    public Remote(String action,String from, String to, String userName, String token, int fanOption,WebSocketMessageClientEndpoint webSocket) {
        this.action = action;
        this.from = from;
        this.to = to;
        this.userName = userName;
        this.token = token;
        this.fanOption = fanOption;
        this.webSocket=webSocket;
    }

    public String remoteAction() {

        final String[] response = new String[1];
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setAction(action);
        message.setUserToken(token);
        message.setUserName(userName);
        message.generateHandlerID();
        message.setFanOption(fanOption);

        webSocket.sendMessage(message.encode());

        webSocket.addMessageHandler(message.getHandlerID(), message1 -> {
            response[0] = message1.encode();
            webSocket.removeMessageHandler(message1.getHandlerID());
        });
        int count = 0;
        while (response[0] == null) {
            if (count == 15) {
                message.setAction("CommunicationError");
                response[0] = message.encode();
                //response[0] = "{\"error\":\"Cannot connect to Web Socket Server.\"}";
                break;
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count++;
                }
            }
        }
        return response[0];
    }

    public void setWebSocket(WebSocketMessageClientEndpoint webSocket) {
        this.webSocket = webSocket;
    }


}
