package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.option.Authentication;
import com.simplicite.option.Cache;
import com.simplicite.option.DropDownMenu;
import com.simplicite.utils.Component;
import com.simplicite.utils.Diagram;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.simplicite.utils.DataStore.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.DisplayName.class)
public class SimpliciteTutorial1Test {

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

        if (Authentication.isAuthentificationPage()) {
            Authentication.connect(USERNAME, NEW_PASSWORD);
        }
    }

    @AfterEach
    public void close() {
        DropDownMenu.click(4);
        Cache.click('u');
    }

    @Test
    public void createOrderCli1() {
        Component.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Component.find("demoCliCode", CLI001);
        Component.next();
        Component.next();
        Component.next();

        Component.setQuantity("field_demoOrdQuantity", NUMBER);
        Component.next();

        String totalstr = Integer.toString(NUMBER * PRICE);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        assertEquals(totalweb, totalstr);
        Component.save();
    }

    @Test
    public void createOrderCli2() {
        Component.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Component.find("demoCliCode", CLI002);
        Component.next();
        Component.find("demoSupCode", DY);
        Component.next();
        Component.next();

        Component.setQuantity("field_demoOrdQuantity", NUMBER);
        Component.next();

        String totalstr = Integer.toString(NUMBER * PRICE2);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        assertEquals(totalweb, totalstr);
        Component.save();
    }

    @Test
    public void createOrderCli3() {
        Component.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Component.find("demoCliCode", CLI003);
        Component.next();
        Component.find("demoSupCode", LLED);
        Component.next();
        Component.next();

        Component.setQuantity("field_demoOrdQuantity", NUMBER);
        Component.next();

        String totalstr = Integer.toString(NUMBER * PRICE3);
        String totalweb = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Component.save();

        assertEquals(totalweb, totalstr);
    }

    @Test
    public void createOrderCli4() {
        Component.clickMenuCreateOrder(DEMO, "DemoOrderCreate");
        Component.find("demoCliCode", CLI004);
        Component.next();
        Component.find("demoSupCode", PEAR);
        Component.next();
        Component.next();

        Component.setQuantity("field_demoOrdQuantity", NUMBER);
        Component.next();

        Diagram.switchProcessingState("V");
        Component.save();

        assertTrue(Diagram.verifyState("select2-field_demoOrdStatus-container", "Validated"));
    }
}
