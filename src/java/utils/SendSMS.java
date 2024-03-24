/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author Gasana
 */
public class SendSMS {
    private final String from_number="+19197596496";
    private static final String ACCOUNT_SID = "ACf244f4cf24007a62d1af3b399fc0ba3d";
    private static final String AUTH_TOKEN = "dc4281d2fa26eede7d57f0252c95c1ae";

    
    public void sendSMS(String to_number,String from_number, String body){
    
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to_number), // TO
                new com.twilio.type.PhoneNumber(from_number), // FROM
                body)
            .create();
//        System.out.println("\n TO :"+to_number +"\n"
//                + "YOU ORDER HAVE BEEN ACCEPTED SUCCESSFULLY");
    }
}