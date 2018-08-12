package webSocket;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketController {

    public WebSocketClientEndpoint setWebSocket() throws URISyntaxException {


        // open websocket
        final WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint(new URI("ws://localhost:8080/iot/iot/server"));

        //clientEndPoint.sendMessage("{'action':'addChannel'}");

        return clientEndPoint;
    }
}
