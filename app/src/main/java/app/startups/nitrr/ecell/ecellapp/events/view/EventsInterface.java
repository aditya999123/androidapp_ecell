package app.startups.nitrr.ecell.ecellapp.events.view;

import java.util.List;

public interface EventsInterface {
    void ShowProgressBar(boolean show);
    void SetData(List<EventsData> eventDataList);
}
