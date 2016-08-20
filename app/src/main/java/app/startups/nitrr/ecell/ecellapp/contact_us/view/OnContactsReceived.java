package app.startups.nitrr.ecell.ecellapp.contact_us.view;

import java.util.List;

/**
 * Created by Iket on 8/20/2016.
 */
public interface OnContactsReceived {
    void onFailure();
    void onSuccess(List<ContactsData> contactDataList);
}
