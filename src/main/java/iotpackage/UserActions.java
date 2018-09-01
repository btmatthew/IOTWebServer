package iotpackage;


import webSocket.User;
import webSocket.WebSocketUserClientEndpoint;


class UserActions {

    private WebSocketUserClientEndpoint webSocket;

    UserActions(WebSocketUserClientEndpoint webSocket) {
        this.webSocket = webSocket;
    }


    String registerUser(User user) {
        final String[] returnUser = new String[1];
        if (!user.confirmFieldsForRegistration()) {
            user.setAction("registerUser");
            webSocket.sendMessage(user.encode());

            webSocket.addUserHandler(user1 -> returnUser[0] = user1.encode());

            int count = 0;
            while (returnUser[0] == null) {
                if (count == 15) {
                    user.setAction("CommunicationError");
                    user.purgeConfidentialData();
                    returnUser[0] = user.encode();
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
        } else {
            user.setAction("DataMissing");
            user.purgeConfidentialData();
            returnUser[0] = user.encode();
        }
        return returnUser[0];
    }

    String userLogin(User user) {
        final String[] returnUser = new String[1];
        if (!user.confirmFieldsForLogin()) {
            user.setAction("userLogin");
            webSocket.sendMessage(user.encode());

            webSocket.addUserHandler(user1 -> returnUser[0] = user1.encode());

            int count = 0;
            while (returnUser[0] == null) {
                if (count == 15) {
                    user.setAction("CommunicationError");
                    user.purgeConfidentialData();
                    returnUser[0] = user.encode();
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
        } else {
            user.setAction("DataMissing");
            user.purgeConfidentialData();
            returnUser[0] = user.encode();
        }
        return returnUser[0];
    }



}
