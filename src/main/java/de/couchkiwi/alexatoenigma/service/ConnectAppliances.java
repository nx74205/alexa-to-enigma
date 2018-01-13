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

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Value(("${receivers_config}"))
    private String fileName;

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

        for ( int i=0; i < receivers.length; i++) {
            CallEnigmaHubService cehs = new CallEnigmaHubService("https://enigmahub.swimming-kiwi.de:443", "/commands", receivers[i], alexaCommandService);
            cehs.start();

        }
    }
}
