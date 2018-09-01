package webSocket;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketController {

    String ipAddress = "localhost";
    //String ipAddress = "192.168.1.6";

    public WebSocketMessageClientEndpoint setDevicesWebSocket() throws URISyntaxException {

        // open websocket for device purposes
        final WebSocketMessageClientEndpoint clientEndPoint = new WebSocketMessageClientEndpoint(new URI("ws://"+ipAddress+":8080/iot/iot/1"));

        return clientEndPoint;
    }

    public WebSocketUserClientEndpoint setUsersWebSocket() throws URISyntaxException {

        // open websocket for user purposes
        final WebSocketUserClientEndpoint clientEndPoint = new WebSocketUserClientEndpoint(new URI("ws://"+ipAddress+":8080/iot/user/1"));
        return clientEndPoint;
    }

}
