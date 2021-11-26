package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.Simplinium.Process;
import com.simplicite.Simplinium.SessionManagement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.simplicite.Simplinium.Form.*;
import static com.simplicite.Simplinium.General.clickMenuProcess;
import static com.simplicite.Simplinium.List.find;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    }

    @AfterAll
    public static void setDownAll() {
        try {
            var out = new FileWriter("src/test/resources/config.properties");
            PROPERTIES.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        open(PROPERTIES.getProperty("url"));

        if (SessionManagement.isAuthentificationPage()) {
            SessionManagement.connect(PROPERTIES.getProperty("name"), PROPERTIES.getProperty("password"));
        }
    }

    @Test
    public void createOrderCli1() {
        int NUMBER = 5;
        int PRICE = 850;

        clickMenuProcess("DemoDomain", "DemoOrderCreate");
        find("demoCliCode", "CLI001");
        Process.nextPage();
        Process.nextPage();
        Process.nextPage();

        setSliderValue("field_demoOrdQuantity", NUMBER);
        Process.nextPage();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        assertEquals(totalweb, totalstr);
        save();
    }

    @Test
    public void createOrderCli2() {
        int NUMBER = 5;
        int PRICE = 582;

        clickMenuProcess("DemoDomain", "DemoOrderCreate");
        find("demoCliCode", "CLI002");
        Process.nextPage();
        find("demoSupCode", "DY");
        Process.nextPage();
        Process.nextPage();

        setSliderValue("field_demoOrdQuantity", NUMBER);
        Process.nextPage();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        assertEquals(totalweb, totalstr);
        save();
    }

    @Test
    public void createOrderCli3() {
        int NUMBER = 5;
        int PRICE = 550;

        clickMenuProcess("DemoDomain", "DemoOrderCreate");
        find("demoCliCode", "CLI003");
        Process.nextPage();
        find("demoSupCode", "LLED");
        Process.nextPage();
        Process.nextPage();

        setSliderValue("field_demoOrdQuantity", NUMBER);
        Process.nextPage();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        save();

        assertEquals(totalweb, totalstr);
    }

    @Test
    public void createOrderCli4() {
        int NUMBER = 5;
        int PRICE = 497;

        clickMenuProcess("DemoDomain", "DemoOrderCreate");
        find("demoCliCode", "CLI004");
        Process.nextPage();
        find("demoSupCode", "PEAR");
        Process.nextPage();
        Process.nextPage();

        setSliderValue("field_demoOrdQuantity", PRICE);
        Process.nextPage();

        switchProcessingState("V");
        save();

        assertTrue(verifyState("select2-field_demoOrdStatus-container", "Validated"));
    }
}
