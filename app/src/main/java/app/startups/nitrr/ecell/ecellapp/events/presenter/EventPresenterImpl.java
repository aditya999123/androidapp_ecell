package app.startups.nitrr.ecell.ecellapp.events.presenter;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.events.model.EventsProvider;
import app.startups.nitrr.ecell.ecellapp.events.view.EventsData;
import app.startups.nitrr.ecell.ecellapp.events.view.EventsInterface;
import app.startups.nitrr.ecell.ecellapp.events.view.OnEventsReceived;

/**
 * Created by Iket on 7/27/2016.
 */
public class EventPresenterImpl implements EventsPresenter {
    private EventsProvider eventsProvider;
    private EventsInterface eventsInterface;

    public EventPresenterImpl(EventsInterface eventsInterface, EventsProvider eventsProvider) {
        this.eventsInterface=eventsInterface;
        this.eventsProvider=eventsProvider;

    }

    @Override
    public void requestEvents() {

        eventsInterface.ShowProgressBar(true);
        eventsProvider.requestEvents(new OnEventsReceived() {
            @Override
            public void onSuccess(List<EventsData> eventDataList) {

                eventsInterface.SetData(eventDataList);
                eventsInterface.ShowProgressBar(false);
            }

            @Override
            public void onFailure() {
                eventsInterface.ShowProgressBar(false);

            }
        });


    }
}
