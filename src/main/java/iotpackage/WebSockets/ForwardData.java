package iotpackage.WebSockets;

import webSocket.WebSocketMessageClientEndpoint;
import webSocket.objects.Message;

public class ForwardData {
    private Message message;
    private WebSocketMessageClientEndpoint webSocket;
    private String jsonReply;

    //Sensor

    /***
     * Constructor used for purpose of sending data to the sensor
     * @param action what action will the sensor perform
     * @param from where did the request originated
     * @param to which sensor should the be send to
     * @param userName which user requested the action
     * @param token authentication token
     * @param webSocket websocket object that the data will be send on
     */
    public ForwardData(String action, String from, String to, String userName, String token, WebSocketMessageClientEndpoint webSocket) {
        this.message = new Message();
        this.message.setAction(action);
        this.message.setFrom(from);
        this.message.setTo(to);
        this.message.setUserToken(token);
        this.message.setUserName(userName);
        this.webSocket = webSocket;
    }

    //Lamp

    /***
     * Constructor used for purpose of sending data to the lamp device
     * @param action what action will the sensor perform
     * @param from where did the request originated
     * @param to which sensor should the be send to
     * @param userName which user requested the action
     * @param token authentication token
     * @param webSocket websocket object that the data will be send on
     * @param newDeviceDescription used for purpose of changing the name of the device
     */
    public ForwardData(String from, String to, String userName, String token, String action, String newDeviceDescription,
                       WebSocketMessageClientEndpoint webSocket) {
        this.message = new Message();
        this.message.setAction(action);
        this.message.setFrom(from);
        this.message.setTo(to);
        this.message.setUserToken(token);
        this.message.setUserName(userName);
        this.message.setDeviceDescription(newDeviceDescription);
        this.webSocket = webSocket;

    }

    //Remote

    /***
     * Constructor used for purpose of sending data to the remote device
     * @param action what action will the sensor perform
     * @param from where did the request originated
     * @param to which sensor should the be send to
     * @param userName which user requested the action
     * @param token authentication token
     * @param webSocket websocket object that the data will be send on
     */
    public ForwardData(String action, String from, String to, String userName, String token, int fanOption, WebSocketMessageClientEndpoint webSocket) {
        this.message = new Message();
        this.message.setAction(action);
        this.message.setFrom(from);
        this.message.setTo(to);
        this.message.setUserToken(token);
        this.message.setUserName(userName);
        this.message.setFanOption(fanOption);
        this.webSocket = webSocket;
    }

    public void sendAction() {
        this.message.generateHandlerID();
        webSocket.sendMessage(message.encode());

        webSocket.addMessageHandler(message.getHandlerID(), message1 -> {
            reply(message1.encode());
            webSocket.removeMessageHandler(message1.getHandlerID());
        });
    }

    private void reply(String jsonReply) {
        this.jsonReply = jsonReply;
    }

    public String waitForReply() {
        int count = 0;
        while (this.jsonReply == null) {
            if (count == 15) {
                message.setAction("CommunicationError");
                this.jsonReply = message.encode();
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
        return jsonReply;
    }
}
