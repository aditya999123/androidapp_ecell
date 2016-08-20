package app.startups.nitrr.ecell.ecellapp.about_us.view;

/**
 * Created by Iket on 8/20/2016.
 */
public class AboutUsData {
    private String image;
    private String description;
    private int id;

    public AboutUsData(String image, String description, int id) {
        this.image = image;
        this.description = description;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
