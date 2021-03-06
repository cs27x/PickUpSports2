package pickupsports2.ridgewell.pickupsports2.data;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ridgewell.pickupsports2.common.Event;
import ridgewell.pickupsports2.common.Location;
import ridgewell.pickupsports2.common.Sport;
import ridgewell.pickupsports2.common.User;

/**
 * This class provides a set of fake events for testing purposes.
 *
 * Created by jules on 2/2/15.
 */
public class DummyEventSource implements EventSource {

    @Override
    public List<Event> getEventsInDateRange(DateTime start, DateTime end) {

        List<Event> events = new ArrayList<Event>();

        Sport football = new Sport("Football");
        Sport soccer = new Sport("Soccer");
        Date date1 = new Date();
        Date date2 = new Date();
        date2.setHours(-72);
        Location loc1 = new Location("Nashville, TN");
        User user1 = new User("Cameron Ridgewell");
        Event event1 = new Event("Random Name 1", football, date1, loc1, 0, "", false, 30, user1);
        Event event2 = new Event("Random Name 2", soccer, date2, loc1, 3, "notes here", true, 20, user1);

        events.add(event1);
        events.add(event2);

        return events;
    }
}
