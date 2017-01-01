package app.startups.nitrr.ecell.ecellapp.BQuizNew.model;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.OnAnswerSubmitted;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;
import rx.Observable;

/**
 * Created by meghal on 9/8/16.
 */
public interface SubmitAnswerProvider {

    Observable<SubmitAnswerData> submitQuestion(int questionId, String answerId,String access_token);

}
