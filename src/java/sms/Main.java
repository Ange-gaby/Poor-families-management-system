/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

/**
 *
 * @author ECM
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      SendSMS s = new SendSMS();
      String bod = "hey";
      String fromN ="+18563910106";
      s.sendSMS("+250781440051", fromN, bod);
      
      
    }
    
}
