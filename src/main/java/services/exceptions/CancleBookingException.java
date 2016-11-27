package services.exceptions;

import javax.xml.ws.WebFault;


@WebFault(name = "CancleBookingException")
public class CancleBookingException extends Exception {
    public CancleBookingException(String message) {
        super(message);
    }
}
