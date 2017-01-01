package app.startups.nitrr.ecell.ecellapp.welcome.api;

import app.startups.nitrr.ecell.ecellapp.welcome.model.data.SignInData;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by meghal on 8/7/16.
 */
public interface RequestSignInApi {

    // we have loginType 0 for Facebook 1 for Googleplus
    @FormUrlEncoded
    @POST
    Observable<SignInData> requestSignIn(@Query("userId") String userid, @Query("userName") String userName
            , @Query("email") String email, @Query("profilePhoto") String profilePhoto,
                                         @Query("profileLink") String profileLink,
                                         @Query("loginType") int loginType);
}
