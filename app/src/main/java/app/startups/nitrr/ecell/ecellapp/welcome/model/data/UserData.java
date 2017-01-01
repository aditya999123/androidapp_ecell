package app.startups.nitrr.ecell.ecellapp.welcome.model.data;

/**
 * Created by meghal on 8/7/16.
 */
public class UserData {

    private String userId;
    private String userName;
    private String email;
    private String profilePhoto;
    private String firebaseToken;


    public UserData(String userId, String userName, String email, String profilePhoto, String firebaseToken) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.profilePhoto = profilePhoto;
        this.firebaseToken = firebaseToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }
}
