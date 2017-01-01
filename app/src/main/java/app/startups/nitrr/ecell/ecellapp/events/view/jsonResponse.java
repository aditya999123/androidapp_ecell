package app.startups.nitrr.ecell.ecellapp.events.view;

import java.util.List;

/**
 * Created by Iket on 7/27/2016.
 */
public class jsonResponse {
    private List<EventsData> events;

    jsonResponse(List<EventsData> events) {
        this.events = events;
    }

    public List<EventsData> getEvents() {
        return events;
    }
}
