package webSocket.objects;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Message {

    private String from;
    private String to;
    private String action;

    private String handlerID;

    private String lampStatus;

    private String userName;
    private String userToken;

    private String deviceDescription;
    private String deviceID;
    private String deviceType;

    private ArrayList<Message> deviceList;

    private int fanOption;
    private boolean fanStatus;
    private int fanSpeed;
    private int fanMode;
    private boolean rotation;
    private boolean ion;

    private boolean tvStatus;

    private float humidity;
    private float temperature;
    private String tempSensorStatus;

    public Message() {
    }

    public String encode() {
        return new Gson().toJson(this);
    }

    public Message decode(String s) {
        return new Gson().fromJson(s, Message.class);
    }

    public boolean isFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(boolean fanStatus) {
        this.fanStatus = fanStatus;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public int getFanMode() {
        return fanMode;
    }

    public void setFanMode(int fanMode) {
        this.fanMode = fanMode;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }

    public boolean isIon() {
        return ion;
    }

    public void setIon(boolean ion) {
        this.ion = ion;
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


    public String getHandlerID() {
        return handlerID;
    }

    public void generateHandlerID() {
        this.handlerID = String.valueOf(System.currentTimeMillis());
    }

    public String getLampStatus() {
        return lampStatus;
    }

    public void setLampStatus(String lampStatus) {
        this.lampStatus = lampStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public ArrayList<Message> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(ArrayList<Message> deviceList) {
        this.deviceList = deviceList;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getFanOption() {
        return fanOption;
    }

    public void setFanOption(int fanOption) {
        this.fanOption = fanOption;
    }

    public boolean isTvStatus() {
        return tvStatus;
    }

    public void setTvStatus(boolean tvStatus) {
        this.tvStatus = tvStatus;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getTempSensorStatus() {
        return tempSensorStatus;
    }

    public void setTempSensorStatus(String tempSensorStatus) {
        this.tempSensorStatus = tempSensorStatus;
    }
}
