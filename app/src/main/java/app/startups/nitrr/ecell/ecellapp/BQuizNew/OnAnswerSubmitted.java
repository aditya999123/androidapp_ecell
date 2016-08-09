package app.startups.nitrr.ecell.ecellapp.BQuizNew;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;

/**
 * Created by meghal on 9/8/16.
 */
public interface OnAnswerSubmitted {

    void onSuccess(SubmitAnswerData submitAnswerData);

    void onFailure();

}
