package app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data;

/**
 * Created by meghal on 8/8/16.
 */
public class SubmitAnswerData {

    private boolean success;
    private String message;

    private String message_image;
    private String message_display;

    public SubmitAnswerData(boolean success, String message, String message_image, String message_display) {
        this.success = success;
        this.message = message;
        this.message_image = message_image;
        this.message_display = message_display;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage_image() {
        return message_image;
    }

    public String getMessage_display() {
        return message_display;
    }
}
