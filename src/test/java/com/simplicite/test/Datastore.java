package com.simplicite.test;

import java.util.Properties;

public class Datastore {

    public final static String DOMAIN = "DemoDomain";
    public final static String BOBJECT1 = "DemoSupplier";

    public static Properties PROPERTIES = new Properties();

    public static final String ORDER = "AlcOrder";
    public static final String CLIENT = "AlcClient";
    public static final String PRODUCT = "AlcProduct";
    public static final String SUPPLIER = "AlcSupplier";
    public static final String MODULE = "AllComposant";
    public static final String GROUP = "ALC_GROUP";

    public static final String SUPPLIERAREA1 = "AlcSupplier-1";
    public static final String PRODUCTAREA1 = "AlcProduct-1";
    public static final String CLIENTAREA1 = "AlcClient-1";
    public static final String ORDERAREA1 = "AlcOrder-1";
    public static final String ORDERAREA2 = "AlcOrder-2";
    public static final String ORDERAREA3 = "AlcOrder-3";
    public static String NEW_PASSWORD;

    public static final String INCREASESTOCK = "IncreaseStock";
    public static final String FIELDORDERSTATE = "AlcOrdState";
    public static final String REDO = "RedoLog-R";
    public static final String HOME = "AlcHome";
    public static final String PIVOTTABLE = "AlcTcOrders";
    public static final String OLD_PASSWORD = "simplicite";

    public static final String PROCESSING = "PROCESSING";
    public static final String CANCELED = "CANCELED";
    public static final String VALIDATED = "VALIDATED";
    public static final String SENT = "SENT";
    public static final String THEME = "AlcTheme";

    public static final int[][] LISTACCESSORDERSTATE = {{0, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}};
    public static final String[] LISTORDERSTATE = {"Processing", "Canceled", "Validated", "Sent"};
    public static final String[][] LISTTRADORDERSTATE = {
            {"AlcORDSTATE-CANCELED-PROCESSING", "Switch to Processing"},
            {"AlcORDSTATE-CANCELED-VALIDATED", "Back to Validated"},
            {"AlcORDSTATE-PROCESSING-CANCELED", "Cancel"},
            {"AlcORDSTATE-PROCESSING-VALIDATED", "Validate"},
            {"AlcORDSTATE-SENT-VALIDATED", "Back to Validated"},
            {"AlcORDSTATE-VALIDATED-CANCELED", "Cancel"},
            {"AlcORDSTATE-VALIDATED-PROCESSING", "Back to Processing"},
            {"AlcORDSTATE-VALIDATED-SENT", "Send"}
    };

    public static final String ORDERTEMPLATEHTML = """
            <div class="row">
                <div class="col-sm-6">
                  <div class="area" data-area="1:="></div>
                </div>
                <div class="col-sm-6">
                  <div class="area" data-area="2"></div>
                  <div class="area" data-area="3"></div>
                </div>
              </div>""";

    public static String USERNAME;

    public static void initUser() {
        NEW_PASSWORD = PROPERTIES.getProperty("password");
        USERNAME = PROPERTIES.getProperty("name");
    }
}
