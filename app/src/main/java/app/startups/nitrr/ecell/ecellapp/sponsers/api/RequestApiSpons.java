package app.startups.nitrr.ecell.ecellapp.sponsers.api;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.ResponseSpons;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Iket on 8/21/2016.
 */
public interface RequestApiSpons {
    @GET(Urls.REQUEST_SPONS)
    Call<ResponseSpons> getSpons();
}
