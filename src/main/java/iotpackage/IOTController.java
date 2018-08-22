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
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status(@RequestParam(value="id",defaultValue = "") String lampID) throws Exception {
        return new Lamp(lampID,webSocket,serverID).lampStatus();
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public @ResponseBody String createEmployee(@RequestBody User user) throws URISyntaxException {
        WebSocketUserClientEndpoint userWebSocket = new WebSocketController().setUsersWebSocket();
        return new UserActions(userWebSocket).registerUser(user);
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