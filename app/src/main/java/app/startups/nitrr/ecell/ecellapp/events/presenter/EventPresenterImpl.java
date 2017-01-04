package app.startups.nitrr.ecell.ecellapp.events.presenter;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.view.BQuizActivity;
import app.startups.nitrr.ecell.ecellapp.events.model.EventsProvider;
import app.startups.nitrr.ecell.ecellapp.events.view.EventsData;
import app.startups.nitrr.ecell.ecellapp.events.view.EventsInterface;
import app.startups.nitrr.ecell.ecellapp.events.view.OnEventsReceived;
import app.startups.nitrr.ecell.ecellapp.home.view.Home;

/**
 * Created by Iket on 7/27/2016.
 */
public class EventPresenterImpl implements EventsPresenter {
    private EventsProvider eventsProvider;
    private EventsInterface eventsInterface;
    CountDownTimer countDownTimer;


    public EventPresenterImpl(EventsInterface eventsInterface, EventsProvider eventsProvider) {
        this.eventsInterface=eventsInterface;
        this.eventsProvider=eventsProvider;

    }

    @Override
    public void requestEvents() {
        Log.d("ResponseOtp","4");

        eventsInterface.ShowProgressBar(true);
         countDownTimer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                eventsInterface.showMessage( "Slow internet connection..");
            }
        }.start();

        eventsProvider.requestEvents(new OnEventsReceived() {
            @Override
            public void onSuccess(List<EventsData> eventDataList) {

                countDownTimer.cancel();
                eventsInterface.SetData(eventDataList);
                eventsInterface.ShowProgressBar(false);
                Log.d("ResponseOtp","Success");
            }

            @Override
            public void onFailure() {
                countDownTimer.cancel();
                eventsInterface.ShowProgressBar(false);
                eventsInterface.showMessage("No Internet Connection Available");
            }
        });
    }
}
