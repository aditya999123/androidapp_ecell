package app.startups.nitrr.ecell.ecellapp.BQuizNew.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.R;
public class BQuizActivity extends AppCompatActivity implements BQuizView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bquiz_new);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(BQuizActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressbar(boolean show) {

    }

    @Override
    public void setBquizData(BQuizData bquizData) {

    }
}
