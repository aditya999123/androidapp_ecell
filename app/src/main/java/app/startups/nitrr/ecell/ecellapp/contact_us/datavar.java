package app.startups.nitrr.ecell.ecellapp.contact_us;

/**
 * Created by aditya on 17/5/16.
 */
public class datavar {
    private int image_p;
    private String name_p;
    private String dsgn_p;
    private String number_p;
    private String email_p;

    public datavar(int img, String name, String dsgn, String num, String email) {
        this.image_p = img;
        this.name_p = name;
        this.dsgn_p = dsgn;
        this.number_p = num;
        this.email_p = email;
    }


    public int getImage_p() {
        return image_p;
    }

    public String getName_p() {
        return name_p;
    }

    public String getDsgn_p() {
        return dsgn_p;
    }

    public String getNumber_p() {
        return number_p;
    }

    public String getEmail_p() {
        return email_p;
    }
}

