package models;

/**
 * Created by troels on 10/31/16.
 */
public class FlightInformation {

    private String bookingNumber, airlineName;

    private int price;

    private Flight flight;

    public FlightInformation(String bookingNumber, String airlineName, int price, Flight flight) {
        this.bookingNumber = bookingNumber;
        this.airlineName = airlineName;
        this.price = price;
        this.flight = flight;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
