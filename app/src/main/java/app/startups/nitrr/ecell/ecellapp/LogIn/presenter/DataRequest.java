package app.startups.nitrr.ecell.ecellapp.LogIn.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Iket on 8/2/2016.
 */
public class DataRequest implements DataPresenter{
    String w="";boolean a;
    @Override
    public boolean emailInvalid(String email) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        boolean a=matcher.matches();
        return !a;
    }

    @Override
    public String space(String name) {
        char ch;String w="";
        for(int i=0;i<name.length();i++)
        {
            ch=(char)name.charAt(i);
            int j=(int)ch;
            if(j!=32)
                w=w+ch;
            else
                w=w+"%20";

        }
        return w;
    }
}
