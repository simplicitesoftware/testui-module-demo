package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.Simplinium.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.simplicite.test.DataStore.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
public class DemoUiTest {

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
        initUser();

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
            SessionManagement.connect(USERNAME, NEW_PASSWORD);
        }
    }

    @AfterEach
    public void close() {
        MenuInteraction.clickDropDownMenu(4);
        SessionManagement.clearCache('u');
    }

    @Test
    public void createOrderCli1() {
        MenuInteraction.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Research.find("demoCliCode", CLI001);
        Button.next();
        Button.next();
        Button.next();

        Modifier.setSliderValue("field_demoOrdQuantity", NUMBER);
        Button.next();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        assertEquals(totalweb, totalstr);
        Button.save();
    }

    @Test
    public void createOrderCli2() {
        MenuInteraction.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Research.find("demoCliCode", CLI002);
        Button.next();
        Research.find("demoSupCode", DY);
        Button.next();
        Button.next();

        Modifier.setSliderValue("field_demoOrdQuantity", NUMBER);
        Button.next();

        String totalstr = Integer.toString(NUMBER * PRICE2);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        assertEquals(totalweb, totalstr);
        Button.save();
    }

    @Test
    public void createOrderCli3() {
        MenuInteraction.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Research.find("demoCliCode", CLI003);
        Button.next();
        Research.find("demoSupCode", LLED);
        Button.next();
        Button.next();

        Modifier.setSliderValue("field_demoOrdQuantity", NUMBER);
        Button.next();

        String totalstr = Integer.toString(NUMBER * PRICE3);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Button.save();

        assertEquals(totalweb, totalstr);
    }

    @Test
    public void createOrderCli4() {
        MenuInteraction.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Research.find("demoCliCode", CLI004);
        Button.next();
        Research.find("demoSupCode", PEAR);
        Button.next();
        Button.next();

        Modifier.setSliderValue("field_demoOrdQuantity", NUMBER);
        Button.next();

        Modifier.switchProcessingState("V");
        Button.save();

        assertTrue(Condition.verifyState("select2-field_demoOrdStatus-container", "Validated"));
    }
}
