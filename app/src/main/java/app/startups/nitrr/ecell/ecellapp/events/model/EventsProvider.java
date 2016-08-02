package app.startups.nitrr.ecell.ecellapp.events.model;

import app.startups.nitrr.ecell.ecellapp.events.view.OnEventsReceived;

/**
 * Created by Iket on 7/27/2016.
 */
public interface EventsProvider {
    void requestEvents(OnEventsReceived onEventsReceived);
}
