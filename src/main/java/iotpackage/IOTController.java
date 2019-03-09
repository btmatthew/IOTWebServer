package iotpackage;


import iotpackage.WebSockets.ForwardData;
import org.springframework.web.bind.annotation.*;
import webSocket.objects.User;
import webSocket.WebSocketMessageClientEndpoint;
import webSocket.WebSocketUserClientEndpoint;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class IOTController {
    private String ipAddress = "localhost";
    private URI iotURI;
    private WebSocketMessageClientEndpoint webSocket;
    //todo replace this with a generated token
    private String serverID = "1";

    public IOTController() {
        try {
            this.iotURI = new URI("ws://" + ipAddress + ":8080/iot/iot/1");
            this.webSocket = new WebSocketMessageClientEndpoint(this.iotURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/lampAction", method = RequestMethod.GET)
    public String lamp(@RequestParam(value = "deviceId", defaultValue = "") String lampID,
                       @RequestParam(value = "userName", defaultValue = "") String userName,
                       @RequestParam(value = "userToken", defaultValue = "") String token,
                       @RequestParam(value = "lampAction", defaultValue = "") String action,
                       @RequestParam(value = "newDeviceDescription", defaultValue = "") String newDeviceDescription,
                       @RequestParam(value = "relayID", defaultValue = "") String relayID) {
        if(this.webSocket == null || !this.webSocket.socketSession.isOpen()){
            this.webSocket = new WebSocketMessageClientEndpoint(this.iotURI);
        }
        ForwardData forwardData =new ForwardData(serverID, lampID, userName, token, action, newDeviceDescription,relayID,webSocket);
        forwardData.sendAction();
        return forwardData.waitForReply();
    }

    @RequestMapping(value = "/remoteAction", method = RequestMethod.GET)
    public String remote(@RequestParam(value = "action", defaultValue = "") String action,
                         @RequestParam(value = "deviceId", defaultValue = "") String deviceID,
                         @RequestParam(value = "userName", defaultValue = "") String userName,
                         @RequestParam(value = "userToken", defaultValue = "") String token,
                         @RequestParam(value = "remoteOption", defaultValue = "0") int remoteOption){
        if(this.webSocket == null || !this.webSocket.socketSession.isOpen()){
            this.webSocket = new WebSocketMessageClientEndpoint(this.iotURI);
        }
        ForwardData forwardData =new ForwardData(action, serverID, deviceID, userName, token, remoteOption,this.webSocket);
        forwardData.sendAction();
        return forwardData.waitForReply();
    }

    @RequestMapping(value = "/sensorAction", method = RequestMethod.GET)
    public String remote(@RequestParam(value = "action", defaultValue = "") String action,
                         @RequestParam(value = "deviceId", defaultValue = "") String deviceID,
                         @RequestParam(value = "userName", defaultValue = "") String userName,
                         @RequestParam(value = "userToken", defaultValue = "") String token){
        if(this.webSocket == null || !this.webSocket.socketSession.isOpen()){
            this.webSocket = new WebSocketMessageClientEndpoint(this.iotURI);
        }
        ForwardData forwardData =new ForwardData(action, serverID, deviceID, userName, token,webSocket);
        forwardData.sendAction();
        return forwardData.waitForReply();
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public @ResponseBody
    String registerUser(@RequestBody User user) throws URISyntaxException {
        WebSocketUserClientEndpoint userWebSocket = new WebSocketUserClientEndpoint(new URI("ws://" + ipAddress + ":8080/iot/user/1"));
        return new UserActions(userWebSocket).registerUser(user);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public @ResponseBody
    String userLogin(@RequestBody User user) throws URISyntaxException {
        WebSocketUserClientEndpoint userWebSocket = new WebSocketUserClientEndpoint(new URI("ws://" + ipAddress + ":8080/iot/user/1"));
        return new UserActions(userWebSocket).userLogin(user);
    }
}