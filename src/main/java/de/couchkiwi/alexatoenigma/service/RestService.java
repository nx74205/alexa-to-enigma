package de.couchkiwi.alexatoenigma.service;

import de.couchkiwi.alexatoenigma.model.AmazonRequest;
import de.couchkiwi.alexatoenigma.model.AmazonResponse;
import de.couchkiwi.alexatoenigma.ReceiverCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/alexacmd")
public class RestService {

    @Autowired
    ReceiverCommand receiverCommand;

    @RequestMapping(method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AmazonResponse testRequest(@RequestBody AmazonRequest amazonRequest) {


        AmazonResponse ams = receiverCommand.getCommand(amazonRequest);

        return ams;

    }

}
