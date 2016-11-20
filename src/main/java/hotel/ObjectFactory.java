
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
    private final static QName _PenisDate_QNAME = new QName("http://services/", "CustomDate");
    private final static QName _Hotel_QNAME = new QName("http://services/", "Hotel");
    private final static QName _CreditCardFault_QNAME = new QName("http://fastmoney.imm.dtu.dk", "CreditCardFault");
    private final static QName _SQLException_QNAME = new QName("http://services/", "SQLException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hotel
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
     * Create an instance of {@link CreditCardFaultType }
     * 
     */
    public CreditCardFaultType createCreditCardFaultType() {
        return new CreditCardFaultType();
    }

    /**
     * Create an instance of {@link SQLException }
     * 
     */
    public SQLException createSQLException() {
        return new SQLException();
    }

    /**
     * Create an instance of {@link PenisDate }
     * 
     */
    public PenisDate createPenisDate() {
        return new PenisDate();
    }

    /**
     * Create an instance of {@link BookingNumberException }
     * 
     */
    public BookingNumberException createBookingNumberException() {
        return new BookingNumberException();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link HotelArray }
     * 
     */
    public HotelArray createHotelArray() {
        return new HotelArray();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link SqlException }
     * 
     */
    public SqlException createSqlException() {
        return new SqlException();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     * 
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
    }

    /**
     * Create an instance of {@link HotelBookingRequest }
     * 
     */
    public HotelBookingRequest createHotelBookingRequest() {
        return new HotelBookingRequest();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PenisDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "CustomDate")
    public JAXBElement<PenisDate> createPenisDate(PenisDate value) {
        return new JAXBElement<PenisDate>(_PenisDate_QNAME, PenisDate.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fastmoney.imm.dtu.dk", name = "CreditCardFault")
    public JAXBElement<CreditCardFaultType> createCreditCardFault(CreditCardFaultType value) {
        return new JAXBElement<CreditCardFaultType>(_CreditCardFault_QNAME, CreditCardFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SQLException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "SQLException")
    public JAXBElement<SQLException> createSQLException(SQLException value) {
        return new JAXBElement<SQLException>(_SQLException_QNAME, SQLException.class, null, value);
    }

}
