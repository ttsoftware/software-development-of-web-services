package services.exceptions;

import javax.xml.ws.WebFault;

/**
 * Created by troels on 11/21/16.
 */
@WebFault(name = "ItineraryDoesNotExistException")
public class ItineraryDoesNotExistException extends Exception {
    public ItineraryDoesNotExistException(String message) {
        super(message);
    }
}
