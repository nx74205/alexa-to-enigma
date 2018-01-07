package de.couchkiwi.alexatoenigma.model;

import java.io.Serializable;

public class HomeAppliances implements Serializable {
    private Receivers[] receivers;

    public Receivers[] getReceivers() {
        return receivers;
    }

    public void setReceivers(Receivers[] receivers) {
        this.receivers = receivers;
    }

    public static class Receivers  {
        private String receivermodell;
        private String applianceaddress;
        private String receivertyp;
        private String[] capabilities;

        public String getApplianceaddress() {
            return applianceaddress;
        }

        public void setApplianceaddress(String applianceaddress) {
            this.applianceaddress = applianceaddress;
        }

        public String getReceivermodell() {
            return receivermodell;
        }

        public void setReceivermodell(String receivermodell) {
            this.receivermodell = receivermodell;
        }

        public String getReceivertyp() {
            return receivertyp;
        }

        public void setReceivertyp(String receivertyp) {
            this.receivertyp = receivertyp;
        }

        public String[] getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(String[] capabilities) {
            this.capabilities = capabilities;
        }
    }
}


/*
{
	"receivers": [
		{
			"receivermodell": "ET7500",
			"receivertype": "ENIGMA2",
			"capabilities": [
				"Alexa.PowerController",
				"Alexa.Speaker"
			]
		},
		{
			"receivermodell": "ET9000",
            "receivertype": "ENIGMA2",
            "capabilities": [
				"Alexa.PowerController",
				"Alexa.Speaker"
			]
		}
	]

}
 */