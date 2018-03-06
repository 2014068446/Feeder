package automate.capstone.feeder.Utility;

import java.util.regex.Pattern;

/**
 * Created by Allen Cayanan on 3/6/2018.
 */

public class NumberValidator {

    public static boolean isPhoneNumber(String phonenumber){
        boolean result = false;
        if(((phonenumber.charAt(0)=='+')&&(phonenumber.length()==13)&&(phonenumber.substring(1,12).matches("[0-9]+")))
                ||(phonenumber.matches("[0-9]+")&&(phonenumber.length()==11))) {
            result = true;
        }
        return result;
    }

    public static String phoneNumberTrim(String phonenumber){
        phonenumber = phonenumber.replace("-", "");
        phonenumber = phonenumber.replace(" ", "");
        phonenumber = phonenumber.replace("(", "");
        phonenumber = phonenumber.replace(")", "");
        phonenumber = phonenumber.replace("_", "");
        return phonenumber;
    }


    public static boolean isNumber(String number){
        boolean result = false;
        if(number.matches("[0-9]+")) {
            result = true;
        }
        return result;
    }

    public static boolean isIpAddress(String ipaddress){
        boolean result = false;
        Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if (PATTERN.matcher(ipaddress).matches()){
            result = true;
        }
        return result;
    }
}
