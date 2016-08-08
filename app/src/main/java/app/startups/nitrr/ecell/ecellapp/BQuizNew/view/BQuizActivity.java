package app.startups.nitrr.ecell.ecellapp.BQuizNew.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.RetrofitBquizProvider;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.BQuizPresenter;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.BQuizPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.GlideImageLoader;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BQuizActivity extends AppCompatActivity implements BQuizView {

    @BindView(R.id.question_text)
    TextView question_text;

    @BindView(R.id.question_image)
    ImageView question_image;

    @BindView(R.id.points)
    TextView points;

    @BindView(R.id.input_ans)
    EditText input_ans;

    @BindView(R.id.timer)
    TextView timer;

    @BindView(R.id.submit_button)
    Button submit_button;

    @BindView(R.id.rb1)
    RadioButton rb1;

    @BindView(R.id.rb2)
    RadioButton rb2;

    @BindView(R.id.rb3)
    RadioButton rb3;

    @BindView(R.id.rb4)
    RadioButton rb4;
/*

    @BindView(R.id.progress_bar)
    ProgressBar progressbar;
*/

    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    int time;

    private BQuizPresenter bQuizPresenter;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bquiz_new);
        ButterKnife.bind(this);
        bQuizPresenter = new BQuizPresenterImpl(this, new RetrofitBquizProvider());
        bQuizPresenter.getBquizData("123412341234");
        imageLoader = new GlideImageLoader(this);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(BQuizActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressbar(boolean show) {
       /* if (show)
            progressbar.setVisibility(View.VISIBLE);
        else
            progressbar.setVisibility(View.GONE);
   */
    }

    @Override
    public void setBquizData(BQuizData bquizData) {
        switch (bquizData.getData_type()) {


            case 1:
                question_image.setVisibility(View.GONE);
                radio_group.setVisibility(View.GONE);
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);
                break;
            case 2:
                question_image.setVisibility(View.GONE);
                input_ans.setVisibility(View.GONE);
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                rb1.setText(bquizData.getQuestion_data().getOption1());
                rb2.setText(bquizData.getQuestion_data().getOption2());
                rb3.setText(bquizData.getQuestion_data().getOption3());
                rb4.setText(bquizData.getQuestion_data().getOption4());
                question_image.setVisibility(View.VISIBLE);
                imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);

                //add images in the activity
                break;
            case 3:
                radio_group.setVisibility(View.GONE);
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);
                break;
            case 4:
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                rb1.setText(bquizData.getQuestion_data().getOption1());
                rb2.setText(bquizData.getQuestion_data().getOption2());
                rb3.setText(bquizData.getQuestion_data().getOption3());
                rb4.setText(bquizData.getQuestion_data().getOption4());
                imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                question_image.setVisibility(View.VISIBLE);
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);
                //add images in the activity
                break;
            default:



        }
    }

    public void countDown(int time) {
        time *= 1000;
        new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 60000 + " : " + (millisUntilFinished / 1000) % 60 + "secs left");
            }

            public void onFinish() {


            }
        }.start();

    }


}
