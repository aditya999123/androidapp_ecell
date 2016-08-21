package app.startups.nitrr.ecell.ecellapp.BQuizNew.view;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;

/**
 * Created by meghal on 6/8/16.
 */
public interface BQuizView {


    void showMessage(String message);
    void show_Image(String image);

    void showProgressbar(boolean show);

    void setBquizData(BQuizData bquizData);

    void answerSubmitted(SubmitAnswerData submitAnswerData);

}
