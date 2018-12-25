package webSocket;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketController {

    private String ipAddress = "localhost";

    public WebSocketMessageClientEndpoint setDevicesWebSocket() throws URISyntaxException {

        // open websocket for device purposes

        return new WebSocketMessageClientEndpoint(new URI("ws://"+ipAddress+":8080/iot/iot/1"));
    }

    public WebSocketUserClientEndpoint setUsersWebSocket() throws URISyntaxException {

        // open websocket for user purposes
        return new WebSocketUserClientEndpoint(new URI("ws://"+ipAddress+":8080/iot/user/1"));
    }

}
