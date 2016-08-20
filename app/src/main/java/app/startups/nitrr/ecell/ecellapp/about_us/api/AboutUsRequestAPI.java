package app.startups.nitrr.ecell.ecellapp.about_us.api;

import app.startups.nitrr.ecell.ecellapp.about_us.view.AboutUsData;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Iket on 8/20/2016.
 */
public interface AboutUsRequestAPI {
    @GET(Urls.REQUEST_ABOUTUS)
    Call<AboutUsData> getData();

}
