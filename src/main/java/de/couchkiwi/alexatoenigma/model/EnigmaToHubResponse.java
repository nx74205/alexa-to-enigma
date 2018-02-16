package de.couchkiwi.alexatoenigma.model;

import java.io.Serializable;

public class EnigmaToHubResponse implements Serializable {

    private String idToken;
    private String receiverModell;
    private String alexaCommand;
    private String EnigmaApi;
    private String Enigmaparameter;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
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

        result = String.format("Token: %s * Modell: %s * Command %s * Parameter %s", this.idToken, this.receiverModell, this.alexaCommand, this.Enigmaparameter);

        return String.format(result);
    }
}
