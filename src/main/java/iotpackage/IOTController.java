package iotpackage;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webSocket.WebSocketController;
import webSocket.WebSocketClientEndpoint;

import java.net.URISyntaxException;

@RestController
public class IOTController {

    private WebSocketClientEndpoint webSocket;
    //todo in future use ID of server placed in a database
    private String serverID="server";

    public IOTController(){
        System.out.println("IOT created");
        createSocket();
    }

    //http://localhost:8080/status?ip=192.168.0.100
    //todo replace the ip with ID of the lamp and let the system find the IP of the device by querying the DB
    @RequestMapping("/status")
    public String status(@RequestParam(value="ip",defaultValue = "") String lampIP) throws Exception {
        return new Lamp("123",lampIP,webSocket,serverID).lampStatus();
    }

//    @RequestMapping("/on")
//    public String turnLampOn(@RequestParam(value="ip",defaultValue = "") String lampIP) throws Exception {
//        return new Lamp("123",lampIP,webSocket,serverID).turnLampOn();
//    }
//
//    @RequestMapping("/off")
//    public String turnLampOff(@RequestParam(value="ip",defaultValue = "") String lampIP) throws Exception {
//        return new Lamp("123",lampIP,webSocket,serverID).turnLampOff();
//    }

    private void createSocket(){
        try {
            this.webSocket = new WebSocketController().setWebSocket();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}