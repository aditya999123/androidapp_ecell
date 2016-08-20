package app.startups.nitrr.ecell.ecellapp.contact_us.api;

import app.startups.nitrr.ecell.ecellapp.contact_us.view.JsonResponse;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Iket on 8/20/2016.
 */
public interface RequestInterface {
    @GET(Urls.REQUEST_Contacts)
    Call<JsonResponse> getContacts();
}
