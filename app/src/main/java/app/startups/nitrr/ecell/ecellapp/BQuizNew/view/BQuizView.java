package app.startups.nitrr.ecell.ecellapp.BQuizNew.view;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;

/**
 * Created by meghal on 6/8/16.
 */
public interface BQuizView {


    void showMessage(String message);

    void showProgressbar(boolean show);

    void setBquizData(BQuizData bquizData);
}
