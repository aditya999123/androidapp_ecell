package app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data;

/**
 * Created by meghal on 6/8/16.
 */
public class QuestionData {

    private int question_id;
    private String question;
    private String image_url;
    private String points;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int question_duration;

    public QuestionData(int question_id, String question, String image_url,String points,String option1,
                        String option2, String option3, String option4, int question_duration) {
        this.question_id = question_id;
        this.question = question;
        this.image_url = image_url;
        this.points=points;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question_duration = question_duration;
    }

    public String getPoints() {
        return points;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestion() {
        return question;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getQuestion_duration() {
        return question_duration;
    }
}
