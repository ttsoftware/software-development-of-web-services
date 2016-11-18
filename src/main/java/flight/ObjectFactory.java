
package flight;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the flight package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookingNumberException_QNAME = new QName("http://services/", "BookingNumberException");
    private final static QName _FlightReservation_QNAME = new QName("http://services/", "FlightReservation");
    private final static QName _CreditCardFault_QNAME = new QName("http://fastmoney.imm.dtu.dk", "CreditCardFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: flight
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreditCardInfoType }
     * 
     */
    public CreditCardInfoType createCreditCardInfoType() {
        return new CreditCardInfoType();
    }

    /**
     * Create an instance of {@link FlightReservation }
     * 
     */
    public FlightReservation createFlightReservation() {
        return new FlightReservation();
    }

    /**
     * Create an instance of {@link BookingNumberException }
     * 
     */
    public BookingNumberException createBookingNumberException() {
        return new BookingNumberException();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link FlightReservationArray }
     * 
     */
    public FlightReservationArray createFlightReservationArray() {
        return new FlightReservationArray();
    }

    /**
     * Create an instance of {@link CreditCardFaultType }
     * 
     */
    public CreditCardFaultType createCreditCardFaultType() {
        return new CreditCardFaultType();
    }

    /**
     * Create an instance of {@link CreditCardInfoType.ExpirationDate }
     * 
     */
    public CreditCardInfoType.ExpirationDate createCreditCardInfoTypeExpirationDate() {
        return new CreditCardInfoType.ExpirationDate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingNumberException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "BookingNumberException")
    public JAXBElement<BookingNumberException> createBookingNumberException(BookingNumberException value) {
        return new JAXBElement<BookingNumberException>(_BookingNumberException_QNAME, BookingNumberException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "FlightReservation")
    public JAXBElement<FlightReservation> createFlightReservation(FlightReservation value) {
        return new JAXBElement<FlightReservation>(_FlightReservation_QNAME, FlightReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fastmoney.imm.dtu.dk", name = "CreditCardFault")
    public JAXBElement<CreditCardFaultType> createCreditCardFault(CreditCardFaultType value) {
        return new JAXBElement<CreditCardFaultType>(_CreditCardFault_QNAME, CreditCardFaultType.class, null, value);
    }

}
