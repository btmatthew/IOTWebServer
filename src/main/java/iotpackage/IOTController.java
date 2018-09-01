package iotpackage;


import org.springframework.web.bind.annotation.*;
import webSocket.User;
import webSocket.WebSocketController;
import webSocket.WebSocketMessageClientEndpoint;
import webSocket.WebSocketUserClientEndpoint;

import java.net.URISyntaxException;

@RestController
public class IOTController {

    private WebSocketMessageClientEndpoint webSocket;
    private String serverID="1";

    public IOTController(){
        try {
            this.webSocket = new WebSocketController().setDevicesWebSocket();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //http://localhost:80/status?id=192.168.0.100
    @RequestMapping(value = "/lampAction", method = RequestMethod.GET)
    public String status(@RequestParam(value="deviceId",defaultValue = "") String lampID,
                         @RequestParam(value="userName",defaultValue = "") String userName,
                         @RequestParam(value="userToken",defaultValue = "") String token,
                         @RequestParam(value="lampAction",defaultValue = "") String action) throws Exception {

        while(webSocket.userSession==null){
            this.webSocket = new WebSocketController().setDevicesWebSocket();
        }

        Lamp lamp = new Lamp();
        lamp.setWebSocket(webSocket);
        return lamp.lampAction(serverID,lampID,userName,token,action);
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public @ResponseBody String registerUser(@RequestBody User user) throws URISyntaxException {
        WebSocketUserClientEndpoint userWebSocket = new WebSocketController().setUsersWebSocket();
        return new UserActions(userWebSocket).registerUser(user);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public @ResponseBody String userLogin(@RequestBody User user) throws URISyntaxException {
        WebSocketUserClientEndpoint userWebSocket = new WebSocketController().setUsersWebSocket();
        return new UserActions(userWebSocket).userLogin(user);
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

}