package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.simplicite.Simplinium.Process;
import io.simplicite.Simplinium.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.logging.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$;

@ExtendWith({ScreenShooterExtension.class})
public class DemoUiTest {

    private final static Properties PROPERTIES = new Properties();

    @BeforeAll
    public static void setUpAll() {
        try {
            var in = new FileReader("src/test/resources/config.properties");
            PROPERTIES.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = PROPERTIES.getProperty("browsersize");
        Configuration.browser = PROPERTIES.getProperty("browser");
        Configuration.headless = PROPERTIES.getProperty("headless").equals("true");
        Configuration.savePageSource = false;
        Configuration.pageLoadTimeout = Integer.parseInt(PROPERTIES.getProperty("pageLoadTimeout"));
        Configuration.timeout = Integer.parseInt(PROPERTIES.getProperty("timeout"));
        Configuration.pollingInterval = Integer.parseInt(PROPERTIES.getProperty("pollingInterval"));
        Configuration.webdriverLogsEnabled = true;
        //System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);
        Selenide.open(PROPERTIES.getProperty("url"));
    }

    @AfterAll
    public static void  close() {
        var log = Selenide.getWebDriverLogs(LogType.DRIVER, Level.ALL);
        System.out.println("Driver");

        for (var a : log) {
            System.out.println(a);
        }
        System.out.println("Performance");

        log = Selenide.getWebDriverLogs(LogType.PERFORMANCE, Level.ALL);
        for (var a : log) {
            System.out.println(a);
        }
        System.out.println("Browser");

        for(String logEntry : Selenide.getWebDriverLogs(LogType.BROWSER, Level.ALL)) {
            System.out.println(logEntry);
        }
    }

    @BeforeEach
    public void setUp() {
        if (SessionManagement.isAuthentificationPage()) {
            SessionManagement.connect(PROPERTIES.getProperty("name"), PROPERTIES.getProperty("password"));
        }
    }


    @Test
    public void createOrderCli1() {
        int NUMBER = 5;
        int PRICE = 850;

        General.clickMenuProcess("DemoDomain", "DemoOrderCreate");
        List.find("demoCliCode", "CLI001");
        Process.nextPage();
        Process.nextPage();
        Process.nextPage();

        Form.setSliderValue("field_demoOrdQuantity", NUMBER);
        Process.nextPage();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Assertions.assertEquals(totalweb, totalstr);
        Form.save();
    }

    @Test
    public void createOrderCli2() {
        int NUMBER = 5;
        int PRICE = 582;

        General.clickMenuProcess("DemoDomain", "DemoOrderCreate");
        List.find("demoCliCode", "CLI002");
        Process.nextPage();
        List.find("demoSupCode", "DY");
        Process.nextPage();
        Process.nextPage();

        Form.setSliderValue("field_demoOrdQuantity", NUMBER);
        Process.nextPage();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Assertions.assertEquals(totalweb, totalstr);
        Form.save();
    }

    @Test
    public void createOrderCli3() {
        int NUMBER = 5;
        int PRICE = 550;

        General.clickMenuProcess("DemoDomain", "DemoOrderCreate");
        List.find("demoCliCode", "CLI003");
        Process.nextPage();
        List.find("demoSupCode", "LLED");
        Process.nextPage();
        Process.nextPage();

        Form.setSliderValue("field_demoOrdQuantity", NUMBER);
        Process.nextPage();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Form.save();

        Assertions.assertEquals(totalweb, totalstr);
    }

    @Test
    public void createOrderCli4() {

        General.clickMenuProcess("DemoDomain", "DemoOrderCreate");
        List.find("demoCliCode", "CLI004");
        Process.nextPage();
        List.find("demoSupCode", "PEAR");
        Process.nextPage();
        Process.nextPage();

        Form.setSliderValue("field_demoOrdQuantity", 5);
        Process.nextPage();

        Form.switchProcessingState("V");
        Form.save();

        Assertions.assertTrue(Form.verifyState("select2-field_demoOrdStatus-container", "Validated"));
    }
}
