package de.couchkiwi.alexatoenigma.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.couchkiwi.alexatoenigma.model.HomeAppliances;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class ConnectAppliances {

    private HomeAppliances.Receivers[] receivers;
    private HomeAppliances homeAppliances;
    private String idToken;

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Value(("${receivers_config}"))
    private String fileName;

    @Value(("${enigma_hub}"))
    private String hubUrl;

    @Autowired
    AlexaCommandService alexaCommandService;

    public ConnectAppliances() {
    }

    @PostConstruct
    public void startConnection() {

        log.debug("Json-File: " + fileName);

        try {
            ObjectMapper mapper = new ObjectMapper();
            homeAppliances = mapper.readValue(new File(fileName), HomeAppliances.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        receivers = homeAppliances.getReceivers();
        idToken = homeAppliances.getIdToken();

        for ( int i=0; i < receivers.length; i++) {
            // TODO: Needs to be implemented
            CallEnigmaHubService cehs = new CallEnigmaHubService(hubUrl, "/commands", receivers[i], alexaCommandService, idToken);
            cehs.start();

        }
    }
}
