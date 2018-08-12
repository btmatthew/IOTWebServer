package webSocket;

import com.google.gson.Gson;

public class Message {
    private String from;
    private String to;
    private String action;
    private String handlerID;
    private Gson gson = new Gson();

    public Message(){

    }

    public Message(String from, String to, String action){
        this.from=from;
        this.to=to;
        this.action=action;
        this.handlerID= String.valueOf(System.currentTimeMillis());
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String encode(){
        return gson.toJson(this);
    }

    public Message decode(String s){
        return gson.fromJson(s, Message.class);
    }


    public String getHandlerID() {
        return handlerID;
    }
}
