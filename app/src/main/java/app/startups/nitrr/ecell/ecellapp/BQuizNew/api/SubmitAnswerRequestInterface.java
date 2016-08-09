package app.startups.nitrr.ecell.ecellapp.BQuizNew.api;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by meghal on 9/8/16.
 */
public interface SubmitAnswerRequestInterface {

    @FormUrlEncoded
    @POST(Urls.REQUEST_SPLASH_SCREEN)
    Observable<SubmitAnswerData> submitAnswer(@Field("question_id") String questionId, @Field("answer") String answer);

}
