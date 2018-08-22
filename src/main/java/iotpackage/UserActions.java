package iotpackage;


import webSocket.User;
import webSocket.WebSocketUserClientEndpoint;


public class UserActions {

    private WebSocketUserClientEndpoint webSocket;

    UserActions(WebSocketUserClientEndpoint webSocket) {
        this.webSocket = webSocket;
    }


    String registerUser(User user) {
        final String[] returnUser = new String[1];

        webSocket.sendMessage(user.encode());

        webSocket.addUserHandler(user1 -> returnUser[0] =user1.encode());

        int count =0;
        while(returnUser[0]==null){
            if(count==15){
                returnUser[0] = "{\"error\":\"Cannot connect to Web Socket Server.\"}";

                break;
            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    count++;
                }
            }
        }
        return returnUser[0];
    }
}
