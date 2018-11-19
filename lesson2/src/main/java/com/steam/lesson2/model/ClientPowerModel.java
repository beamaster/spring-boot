package com.steam.lesson2.model;

public class ClientPowerModel {

    private String hostName;
    private String clientId;
    private String clientName;
    private String dataTime;
    private String chargePower;
    private String discChargePower;

    public ClientPowerModel(){}

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getChargePower() {
        return chargePower;
    }

    public void setChargePower(String chargePower) {
        this.chargePower = chargePower;
    }

    public String getDiscChargePower() {
        return discChargePower;
    }

    public void setDiscChargePower(String discChargePower) {
        this.discChargePower = discChargePower;
    }
}
