package app.startups.nitrr.ecell.ecellapp.contact_us.view;

/**
 * Created by Iket on 8/20/2016.
 */
public class ContactsData {
    private int id;
    private String name;
    private String designation;
    private String phone;
    private String email;
    private String image;

    public ContactsData(int id, String name, String dedignation, String phone, String email, String image) {
        this.id = id;
        this.name = name;
        this.designation = dedignation;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }
}
