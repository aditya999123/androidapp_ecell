package app.startups.nitrr.ecell.ecellapp.events.api;

import app.startups.nitrr.ecell.ecellapp.events.view.jsonResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Iket on 7/27/2016.
 */
public interface RequestInterface {
    @GET("/event.txt")
    Call<jsonResponse> getEvents();


}
