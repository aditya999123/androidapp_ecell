package app.startups.nitrr.ecell.ecellapp.BQuizNew.model;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.OnBQuizDataResponse;

/**
 * Created by meghal on 6/8/16.
 */
public interface BQuizProvider {

    void requestBquizData(String adminToken, OnBQuizDataResponse onBQuizDataResponse);

}
