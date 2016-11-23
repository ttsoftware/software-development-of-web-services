package models;

import javax.xml.bind.annotation.*;

@XmlType(name = "ItineraryChangeRequest")
@XmlRootElement(name = "ItineraryChangeRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItineraryChangeRequest {

    private Itinerary itinerary;
    private CreditCardInfoType cardInformation;

    public ItineraryChangeRequest() {
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public CreditCardInfoType getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CreditCardInfoType cardInformation) {
        this.cardInformation = cardInformation;
    }
}
