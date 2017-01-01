package app.startups.nitrr.ecell.ecellapp.events.api;

import app.startups.nitrr.ecell.ecellapp.events.view.jsonResponse;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Iket on 7/27/2016.
 */
public interface RequestInterface {
    @GET(Urls.REQUEST_EVENTS)
    Call<jsonResponse> getEvents();


}
