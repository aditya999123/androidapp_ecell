package app.startups.nitrr.ecell.ecellapp.contact_us.presenter;

import android.util.Log;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.contact_us.model.ContactsProvider;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.ContactsData;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.ContactsInterface;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.OnContactsReceived;

/**
 * Created by Iket on 8/20/2016.
 */
public class ContactsPresenterImpl implements ContactsPresenter{
    private ContactsProvider contactsProvider;
    private ContactsInterface contactsInterface;

    public ContactsPresenterImpl( ContactsInterface contactsInterface,ContactsProvider contactsProvider) {
        this.contactsInterface = contactsInterface;
        this.contactsProvider =  contactsProvider;

    }

    @Override
    public void requestContacts() {
        contactsInterface.ShowProgressBar(true);
        contactsProvider.requestContacts(new OnContactsReceived() {

            @Override
            public void onFailure() {
                contactsInterface.ShowProgressBar(false);
                Log.d("ResponseOtp","Fail");
            }

            @Override
            public void onSuccess(List<ContactsData> contactDataList) {

                Log.d("ResponseOtp","Success");
                contactsInterface.SetData(contactDataList);
                contactsInterface.ShowProgressBar(false);

            }
        });
    }
}
