package webSocket;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketController {

    public WebSocketMessageClientEndpoint setDevicesWebSocket() throws URISyntaxException {

        // open websocket for device purposes
        final WebSocketMessageClientEndpoint clientEndPoint = new WebSocketMessageClientEndpoint(new URI("ws://localhost:8080/iot/iot/1"));

        return clientEndPoint;
    }

    public WebSocketUserClientEndpoint setUsersWebSocket() throws URISyntaxException {

        // open websocket for user purposes
        final WebSocketUserClientEndpoint clientEndPoint = new WebSocketUserClientEndpoint(new URI("ws://localhost:8080/iot/user/1"));
        return clientEndPoint;
    }

}
