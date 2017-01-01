package app.startups.nitrr.ecell.ecellapp.contact_us.model;

import app.startups.nitrr.ecell.ecellapp.contact_us.view.OnContactsReceived;

/**
 * Created by Iket on 8/20/2016.
 */
public interface ContactsProvider {
    void requestContacts(OnContactsReceived onContactsReceived);
}
