package de.couchkiwi.alexatoenigma.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class EnigmaToHubRequest implements Serializable {
    private String eMailAddress;
    private String receiverModell;
    private List<String> capabilities;
    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public String getReceiverModell() {
        return receiverModell;
    }

    public void setReceiverModell(String receiverModell) {
        this.receiverModell = receiverModell;
    }

    @Override
    public String toString() {
        String result;

        result = "E-Mail: " + this.eMailAddress + " * " +
                 "ReceiverModell: " + this.receiverModell + " * ";

        for (String entry : capabilities) {
            result = result + "Cap: " + entry + " * ";
        }

        return String.format(result);
    }
}
