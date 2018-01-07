package de.couchkiwi.alexatoenigma;

import de.couchkiwi.alexatoenigma.model.EnigmaRequest;
import de.couchkiwi.alexatoenigma.service.AlexaCommandService;
import de.couchkiwi.alexatoenigma.service.CallEnigmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class ReceiverCommand {


    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private AlexaCommandService alexaCommandService;

    public ReceiverCommand(AlexaCommandService alexaCommandService) {

        this.alexaCommandService = alexaCommandService;
        //log.debug("Running Constructor");
    }



    public  String call(String commandToken, String targetAddress, int iterations) {

        String responseText;
        EnigmaRequest e;
        String enigmaUrl;

        CallEnigmaService callEnigmaService = new CallEnigmaService();

        e = alexaCommandService.getCommand(commandToken);
        enigmaUrl = "http://" + targetAddress + "/api/";

        if (e != null) {
            if (callEnigmaService.sendCommand(enigmaUrl, e.getApi(), e.getParameter())) {

                if (e.getNextAlexaCommand().length() > 2 && iterations < 5) {                                               // make sure, that we will terminate after 4 rounds latest.
                    responseText = this.call(e.getNextAlexaCommand(), targetAddress ,iterations + 1);
                } else
                    responseText = e.getOkText();
            } else {
                responseText = e.getErrorText();
            }
        } else {
            responseText = "Diese Anweisung verstehe ich leider nicht!";
        }
        return responseText;
    }
}