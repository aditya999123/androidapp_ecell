package app.startups.nitrr.ecell.ecellapp.BQuizNew;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;

/**
 * Created by meghal on 6/8/16.
 */
public interface OnBQuizDataResponse {

    void onSuccess(BQuizData bQuizData);
    void onFailure();
}
