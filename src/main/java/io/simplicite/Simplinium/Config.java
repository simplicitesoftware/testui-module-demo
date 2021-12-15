package io.simplicite.Simplinium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;

import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Config {
    public static String url;
    public static String user;
    public static String password;

    private final static String DEFAULT_SELENIDE_BROWSER = "chrome";
    private final static String DEFAULT_SELENIDE_BROWSERSIZE = "2500x2000";
    private final static String DEFAULT_SELENIDE_HEADLESS = "true";
    private final static String DEFAULT_SELENIDE_SAVEPAGESOURCE = "false";
    private final static String DEFAULT_SELENIDE_PAGELOADTIMEOUT = "30000";
    private final static String DEFAULT_SELENIDE_POLLINGINTERVAL = "1000";
    private final static String DEFAULT_SIMPLICITE_USER = "designer";
    private final static String DEFAULT_SELENIDE_PASSWORD = "simplicite";

    public static  void init(){
        Configuration.browser = System.getProperty("selenide.browser", DEFAULT_SELENIDE_BROWSER);
        Configuration.browserSize = System.getProperty("selenide.browserSize", DEFAULT_SELENIDE_BROWSERSIZE);
        Configuration.headless = System.getProperty("selenide.headless", DEFAULT_SELENIDE_HEADLESS).equals("true");
        Configuration.savePageSource = System.getProperty("selenide.savePageSource", DEFAULT_SELENIDE_SAVEPAGESOURCE).equals("true");
        Configuration.pageLoadTimeout = Integer.parseInt(System.getProperty("selenide.pageLoadTimeout", DEFAULT_SELENIDE_PAGELOADTIMEOUT));
        Configuration.pollingInterval = Integer.parseInt(System.getProperty("selenide.pollingInterval", DEFAULT_SELENIDE_POLLINGINTERVAL));

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);
        
        url=System.getProperty("simplicite.url");
        if(url==null)
            fatalError("No Simplicité url given. Please pass the system property through -Dsimplicite.url=http://my.instance.com");

        user = System.getProperty("simplicite.user", DEFAULT_SIMPLICITE_USER);
        password = System.getProperty("simplicite.password", DEFAULT_SELENIDE_PASSWORD);
    }

    public static void saveBrowserLogs(){
        if(Configuration.browser.equals("firefox"))
            logError("Browser logs saving only available for Chrome (-Dselenide.browser=chrome)");
        else{
            try{
                OutputStream out = new BufferedOutputStream(Files.newOutputStream(
                    Paths.get("./build/reports/tests/com/browser.log"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
                ));
                for (String row : Selenide.getWebDriverLogs(LogType.BROWSER, Level.ALL)) {
                    byte[] data = (row + "\n").getBytes(StandardCharsets.UTF_8);
                    out.write(data, 0, data.length);
                }
            } catch (Exception x) {
                x.printStackTrace();
                System.err.println(x.getMessage());
            }
        }

    }

    public static void log(String m){
        System.out.println("------ "+m);
    }

    public static void logError(String m){
        System.err.println("======= "+m);
    }

    public static void fatalError(String m){
        logError(m);
        System.exit(1);
    }
}