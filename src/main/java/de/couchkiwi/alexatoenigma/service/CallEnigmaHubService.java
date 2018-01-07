package de.couchkiwi.alexatoenigma.service;

import de.couchkiwi.alexatoenigma.ReceiverCommand;
import de.couchkiwi.alexatoenigma.model.EnigmaToHubRequest;
import de.couchkiwi.alexatoenigma.model.EnigmaToHubResponse;
import de.couchkiwi.alexatoenigma.model.HomeAppliances;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class CallEnigmaHubService extends Thread {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String url;
    private String endpoint;
    private HomeAppliances.Receivers receivers;
    private AlexaCommandService alexaCommandService;

    public CallEnigmaHubService(java.lang.String url, java.lang.String endpoint, HomeAppliances.Receivers receivers, AlexaCommandService alexaCommandService) {
        this.url = url;
        this.endpoint = endpoint;
        this.receivers = receivers;
        this.alexaCommandService = alexaCommandService;
    }

    public void run() {

        EnigmaToHubRequest enigmaToHubRequest = new EnigmaToHubRequest();
        List<String> capabilities = new ArrayList<String>();
        boolean runloop = true;

        for(int j=0; j < receivers.getCapabilities().length; j++) {
            capabilities.add(receivers.getCapabilities()[j]);

        }


        enigmaToHubRequest.seteMailAddress("kvolmer@kiwifans.de");
        enigmaToHubRequest.setReceiverModell(receivers.getReceivermodell());
        enigmaToHubRequest.setCapabilities(capabilities);

        log.debug(enigmaToHubRequest.toString());

        while (runloop) {
            if (pingAppliance(receivers.getApplianceaddress())) {
                while (pingAppliance(receivers.getApplianceaddress())) {

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity request = new HttpEntity(enigmaToHubRequest, headers);
                    RestTemplate restTemplate = new RestTemplate();

                    EnigmaToHubResponse response = null;
                    log.debug("Starting Request!");

                    log.debug(url + endpoint);
                    response = restTemplate.postForObject(url + endpoint, request, EnigmaToHubResponse.class);

                    log.debug(String.format("Finished request! Respons: %s", response.toString()));

                    if (!response.getAlexaCommand().equals("NO_COMMAND")) {
                        log.debug("Calling Receiver! with " + response.getAlexaCommand());
                        ReceiverCommand rc = new ReceiverCommand(alexaCommandService);
                        rc.call(response.getAlexaCommand(), receivers.getApplianceaddress(), 1);
                    }


                }
            } else {
                log.debug("Waiting 20 Seconds before next try!");
                try
                {
                    Thread.sleep(20000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private boolean pingAppliance(String appliance) {
        boolean reachable;

        try {
            reachable = InetAddress.getByName(receivers.getApplianceaddress()).isReachable(100);
        } catch (IOException e) {
            reachable = false;
        }

        log.debug(String.format("Receiver %s reachable %s", receivers.getApplianceaddress(), reachable));

        return reachable;

    }
}
