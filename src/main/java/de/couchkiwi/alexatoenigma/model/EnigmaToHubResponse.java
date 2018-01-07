package de.couchkiwi.alexatoenigma.model;

import java.io.Serializable;

public class EnigmaToHubResponse implements Serializable {

    private String eMailAddress;
    private String receiverModell;
    private String alexaCommand;
    private String EnigmaApi;
    private String Enigmaparameter;

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

    public String getAlexaCommand() {
        return alexaCommand;
    }

    public void setAlexaCommand(String alexaCommand) {
        this.alexaCommand = alexaCommand;
    }

    public String getEnigmaApi() {
        return EnigmaApi;
    }

    public void setEnigmaApi(String enigmaApi) {
        EnigmaApi = enigmaApi;
    }

    public String getEnigmaparameter() {
        return Enigmaparameter;
    }

    public void setEnigmaparameter(String enigmaparameter) {
        Enigmaparameter = enigmaparameter;
    }

    @Override
    public String toString() {
        String result;

        result = String.format("E-Mail: %s * ReceiverModell: %s * AlexaCommand %s", this.eMailAddress, this.receiverModell, this.alexaCommand);

        return String.format(result);
    }
}
