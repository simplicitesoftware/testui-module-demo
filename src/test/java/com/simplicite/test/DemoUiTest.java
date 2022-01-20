package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.simplicite.simplinium.Process;
import io.simplicite.simplinium.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$;

@ExtendWith({ScreenShooterExtension.class})
public class DemoUiTest {

    private final static Properties PROPERTIES = new Properties();

    @BeforeAll
    public static void setUpAll() {
        Config.init();
        Selenide.open(Config.url);
    }

    @BeforeEach
    public void setUp() {
        if (SessionManagement.isAuthentificationPage())
            SessionManagement.connect(Config.user, Config.password);
    }

    @AfterAll
    public static void close() {
        Config.saveBrowserLogs();
    }

    @BeforeEach
    public void setUp() {
        if (SessionManagement.isAuthentificationPage()) {
            SessionManagement.connect(PROPERTIES.getProperty("name"), PROPERTIES.getProperty("password"));
        }
    }


    @Test
    public void createOrderCli1() {
        createOrderThroughProcess("CLI001", "BIM", 5, 850);
    }

    @Test
    public void createOrderCli2() {
        createOrderThroughProcess("CLI002", "DY", 5, 582);
    }

    @Test
    public void createOrderCli3() {
        createOrderThroughProcess("CLI003", "LLED", 5, 550);
    }

    @Test
    public void createOrderCli4() {
        createOrderThroughProcess("CLI004", "PEAR", 5, 497);

        Form.switchProcessingState("V");
        Form.save();

        Assertions.assertEquals("V", $("#field_demoOrdStatus").getSelectedOption().getValue());
    }

    private void createOrderThroughProcess(String cliCode, String supCode, int quantity, int expectedPrice) {
        General.clickMenuProcess("DemoDomain", "DemoOrderCreate");

        // Select client
        List.find("demoCliCode", cliCode);
        Process.nextPage();

        // Select Supplier
        List.find("demoSupCode", supCode);
        Process.nextPage();

        // Select product (first one by default)
        Process.nextPage();

        // Select quantity
        Form.setSliderValue("field_demoOrdQuantity", quantity);
        Process.nextPage();

        String expectedTotal = Integer.toString(quantity * expectedPrice);
        String foundTotal = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Assertions.assertEquals(expectedTotal, foundTotal);
        Form.save();
    }
}
