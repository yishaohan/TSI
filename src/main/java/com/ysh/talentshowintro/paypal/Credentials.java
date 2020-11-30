package com.ysh.talentshowintro.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class Credentials {
    final static String clientID = "AYsnjLgEmwy2RIhy0QdO335NCySWCFKnxecxVmVhTVUxLE8hdw_ZNs1LvGD5sH61BErt2JxRuTc7I9Pn";
    final static String secret = "EPG8TJiiiHlA5wJOjqM_4AG0ddWeDPupGKdGWpONfcGRwY2auStDXBTbJjbT2Hi2Bzfn9ZOD1lmBrTbJ";
    final private static PayPalEnvironment env = new PayPalEnvironment.Sandbox(clientID, secret);
    public final static PayPalHttpClient paypalClient = new PayPalHttpClient(env);
}
