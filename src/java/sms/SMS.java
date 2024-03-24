package sms;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.NewKey;

public class SMS {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("ACd41a133cb7e5d241f25c106bcaa6a538");
    public static final String AUTH_TOKEN = System.getenv("587b5170d12f77222aa61289b68443ee");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        NewKey newKey = NewKey.creator().create();

        System.out.println(newKey.getSid());
    }
}
