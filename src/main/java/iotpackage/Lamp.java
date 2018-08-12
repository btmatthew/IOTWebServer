package iotpackage;

import webSocket.Message;
import webSocket.WebSocketClientEndpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class Lamp {

    private String lampID;
    private String sensorValue;
    private String lampIPAddress;
    private String senderID;
    private WebSocketClientEndpoint webSocket;



    public Lamp(String lampID, String lampIPAddress, WebSocketClientEndpoint webSocket,String senderID) {
        this.lampID = lampID;
        this.lampIPAddress=lampIPAddress;
        this.webSocket = webSocket;
        this.senderID=senderID;
    }

    public Lamp(String sensorValue){
        this.sensorValue=sensorValue;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getLampID() {
        return lampID;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public String getLampIPAddress(){
        return lampIPAddress;
    }

    public String lampStatus() throws IOException {
        final String[] response = new String[1];
        Message message = new Message(getSenderID(),"esp8266","status");

        webSocket.sendMessage(message.encode());

        webSocket.addMessageHandler(message.getHandlerID(), new WebSocketClientEndpoint.MessageHandler() {
            @Override
            public void handleMessage(Message message) {
                response[0] = message.encode();
                System.out.println("handler");
                System.out.println(message.encode());
                webSocket.removeMessageHandler(message.getHandlerID());
            }
        });
        while(response[0]==null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        String url = "http://"+lampIPAddress+"/status";
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
        return response[0];
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
//        Lamp emp = objectMapper.readValue(lampStatus(), Lamp.class);
//        System.out.println(emp.sensorValue);
//        return emp.lampStatus();
//    }
}
