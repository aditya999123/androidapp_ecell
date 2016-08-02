package app.startups.nitrr.ecell.ecellapp.events.view;

import java.util.List;

/**
 * Created by Iket on 7/27/2016.
 */
public interface OnEventsReceived {

    void onFailure();
    void onSuccess(List<EventsData> eventDataList);
}
