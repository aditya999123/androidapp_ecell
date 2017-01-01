package app.startups.nitrr.ecell.ecellapp.home.api;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Meghal on 6/19/2016.
 */
public interface HomeDetailsRequestInterface {
    @GET(Urls.REQUEST_HOME)
    Call<HomeData> getHomeData();

}
