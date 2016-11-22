
package hotel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hotel package. 
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
    private final static QName _Hotel_QNAME = new QName("http://services/", "Hotel");
    private final static QName _CustomDate_QNAME = new QName("http://services/", "CustomDate");
    private final static QName _CreditCardFault_QNAME = new QName("http://fastmoney.imm.dtu.dk", "CreditCardFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hotel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreditCardFaultType }
     * 
     */
    public CreditCardFaultType createCreditCardFaultType() {
        return new CreditCardFaultType();
    }

    /**
     * Create an instance of {@link BookingNumberException }
     * 
     */
    public BookingNumberException createBookingNumberException() {
        return new BookingNumberException();
    }

    /**
     * Create an instance of {@link CustomDate }
     * 
     */
    public CustomDate createCustomDate() {
        return new CustomDate();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link HotelArray }
     * 
     */
    public HotelArray createHotelArray() {
        return new HotelArray();
    }

    /**
     * Create an instance of {@link CreditCardInfoType }
     * 
     */
    public CreditCardInfoType createCreditCardInfoType() {
        return new CreditCardInfoType();
    }

    /**
     * Create an instance of {@link HotelBookingRequest }
     * 
     */
    public HotelBookingRequest createHotelBookingRequest() {
        return new HotelBookingRequest();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Hotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "Hotel")
    public JAXBElement<Hotel> createHotel(Hotel value) {
        return new JAXBElement<Hotel>(_Hotel_QNAME, Hotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "CustomDate")
    public JAXBElement<CustomDate> createCustomDate(CustomDate value) {
        return new JAXBElement<CustomDate>(_CustomDate_QNAME, CustomDate.class, null, value);
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
