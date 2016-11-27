package services.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "ItineraryDoesNotExistException")
public class ItineraryDoesNotExistException extends Exception {
    public ItineraryDoesNotExistException(String message) {
        super(message);
    }
}
