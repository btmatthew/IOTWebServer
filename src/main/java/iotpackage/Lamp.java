package iotpackage;

import webSocket.Message;
import webSocket.WebSocketMessageClientEndpoint;

import java.io.IOException;

public class Lamp {

    private WebSocketMessageClientEndpoint webSocket;

    String lampAction(String from, String to, String userName, String token, String action) throws IOException {

        final String[] response = new String[1];
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setAction(action);
        message.setUserToken(token);
        message.setUserName(userName);
        message.generateHandlerID();

        webSocket.sendMessage(message.encode());

        webSocket.addMessageHandler(message.getHandlerID(), message1 -> {
            response[0] = message1.encode();
            webSocket.removeMessageHandler(message1.getHandlerID());
        });
        int count = 0;
        while (response[0] == null) {
            if (count == 15) {
                message.setAction("CommunicationError");
                response[0]=message.encode();
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

//    public String turnLampOn() throws IOException {
//        String url = "http://"+lampIPAddress+"/on";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
////        int responseCode = con.getResponseCode();
////        System.out.println("\nSending 'GET' request to URL : " + url);
////        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//        return response.toString();
//    }
//
//    public String turnLampOff() throws IOException {
//        String url = "http://"+lampIPAddress+"/off";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        //int responseCode = con.getResponseCode();
////        System.out.println("\nSending 'GET' request to URL : " + url);
////        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//        return response.toString();
//    }

//    public String jsonToObject() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Lamp emp = objectMapper.readValue(lampAction(), Lamp.class);
//        System.out.println(emp.sensorValue);
//        return emp.lampAction();
//    }
}
