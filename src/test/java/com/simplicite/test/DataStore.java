package com.simplicite.test;

import java.util.Properties;

public class DataStore {

    public static Properties PROPERTIES = new Properties();
    public static final String DEMO = "DemoDomain";
    public static final int NUMBER = 5;
    public static final int PRICE = 850;
    public static final int PRICE2 = 582;
    public static final int PRICE3 = 550;
    public static final int PRICE4 = 497;

    public static final String CLI001 = "CLI001";
    public static final String CLI002 = "CLI002";
    public static final String CLI003 = "CLI003";
    public static final String CLI004 = "CLI004";

    public static final String BIM = "BIM";
    public static final String DY = "DY";
    public static final String LLED = "LLED";
    public static final String PEAR = "PEAR";


    public static String NEW_PASSWORD;
    public static String USERNAME;

    public static void initUser() {
        NEW_PASSWORD = PROPERTIES.getProperty("password");
        USERNAME = PROPERTIES.getProperty("name");
    }
}
