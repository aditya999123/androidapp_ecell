package app.startups.nitrr.ecell.ecellapp.BQuizNew.api;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by meghal on 6/8/16.
 */
public interface BquizRequestInterface {

    @GET(Urls.REQUEST_BQUIZ_DATA)
    Call<BQuizData> getBQuizData(String query);

}
