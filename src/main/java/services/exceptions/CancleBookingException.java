package services.exceptions;

import javax.xml.ws.WebFault;

/**
 * Created by troels on 11/21/16.
 */
@WebFault(name = "CancleBookingException")
public class CancleBookingException extends Exception {
    public CancleBookingException(String message) {
        super(message);
    }
}
