package app.startups.nitrr.ecell.ecellapp.BQuizNew.model;

import android.os.Handler;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.OnBQuizDataResponse;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.QuestionData;

/**
 * Created by meghal on 8/8/16.
 */
public class MockBquizProvider implements BQuizProvider {

    @Override
    public void requestBquizData(String adminToken, final OnBQuizDataResponse onBQuizDataResponse) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                onBQuizDataResponse.onSuccess(getMockData());

            }
        }, 1000);

    }

    BQuizData getMockData() {

        BQuizData bQuizData = new BQuizData(true, "Successful", "none", 1, new QuestionData(

                1, "What is your name", "none",null, null, null, null, null, 120
        ),"rules");

        return bQuizData;
    }
}
