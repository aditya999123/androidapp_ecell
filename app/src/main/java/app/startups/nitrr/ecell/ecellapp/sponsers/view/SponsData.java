package app.startups.nitrr.ecell.ecellapp.sponsers.view;

/**
 * Created by Iket on 8/21/2016.
 */
public class SponsData {
    private String image1,image2;
    private int id;

    public SponsData(String image1, String image2) {
        this.image1 = image1;
        this.image2 = image2;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }
}
