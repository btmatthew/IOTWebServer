package iotpackage.WebSockets;

import webSocket.WebSocketMessageClientEndpoint;
import webSocket.objects.Message;

public class Lamp {

    private String from;
    private String to;
    private String userName;
    private String token;
    private String action;
    private String newDeviceDescription;
    private WebSocketMessageClientEndpoint webSocket;

    public Lamp(String from, String to, String userName, String token, String action, String newDeviceDescription,
                WebSocketMessageClientEndpoint webSocket) {
        this.from = from;
        this.to = to;
        this.userName = userName;
        this.token = token;
        this.action = action;
        this.newDeviceDescription = newDeviceDescription;
        this.webSocket=webSocket;

    }



    public String lampAction() {

        final String[] response = new String[1];
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setAction(action);
        message.setUserToken(token);
        message.setUserName(userName);
        message.generateHandlerID();
        message.setDeviceDescription(newDeviceDescription);

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
