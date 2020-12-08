package com.ysh.talentshowintro.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class Credentials {/*
    final static String clientID = "AYsnjLgEmwy2RIhy0QdO335NCySWCFKnxecxVmVhTVUxLE8hdw_ZNs1LvGD5sH61BErt2JxRuTc7I9Pn";
    final static String secret = "EPG8TJiiiHlA5wJOjqM_4AG0ddWeDPupGKdGWpONfcGRwY2auStDXBTbJjbT2Hi2Bzfn9ZOD1lmBrTbJ";
    final private static PayPalEnvironment env = new PayPalEnvironment.Sandbox(clientID, secret);
    public final static PayPalHttpClient paypalClient = new PayPalHttpClient(env);*/

    final static String clientID = "AViXcCKHnqll0J1Lh6mZNFeZ626CepBfscyBpuhV3jed0e8ZCvcMzP4n10eB2qx_gKtSfoZYxMOQgb1y";
    final static String secret = "EC2bYzGx99Ap7ibkO1_zjzSmm_spTUNnBOq95eDpq6LICZrXCEFGhM65Ioum1JSvrPMNOdjL9rgGN3Nk";
    final private static PayPalEnvironment env = new PayPalEnvironment.Live(clientID, secret);
    public final static PayPalHttpClient paypalClient = new PayPalHttpClient(env);
}
