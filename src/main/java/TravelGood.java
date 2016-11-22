import services.BookingRESTService;
import services.ItineraryRESTService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/travelagency")
public class TravelGood extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(ItineraryRESTService.class);
        s.add(BookingRESTService.class);
        return s;
    }
}
