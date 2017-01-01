package app.startups.nitrr.ecell.ecellapp.contact_us.view;

import java.util.List;

/**
 * Created by Iket on 8/20/2016.
 */
public class JsonResponse {
    private List<ContactsData> contacts;

    public JsonResponse(List<ContactsData> contacts) {
        this.contacts = contacts;
    }

    public List<ContactsData> getContacts() {
        return contacts;
    }
}
