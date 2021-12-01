package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.simplicite.Simplinium.Process;
import io.simplicite.Simplinium.*;
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

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);

        Selenide.open(PROPERTIES.getProperty("url"));
    }

    @AfterAll
    public static void close() {
        Path p = Paths.get("./build/reports/tests/com/browser.log");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING))) {
            for (String logEntry : Selenide.getWebDriverLogs(LogType.BROWSER, Level.ALL)) {

                var data = (logEntry + "\n").getBytes(StandardCharsets.UTF_8);
                out.write(data, 0, data.length);
            }
            System.out.println("[DEMO] Browser log created:"+ System.getProperty("user.dir") + "/build/reports/tests/com/browser.log");
        } catch (IOException x) {
            System.err.println(x.getMessage());
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
        createOrderThroughProcess("CLI001", "BIM", 5, 850);
    }

    @Test
    public void createOrderCli2() {
        createOrderThroughProcess("CLI002", "DY", 7, 582);
    }

    @Test
    public void createOrderCli3() {
        createOrderThroughProcess("CLI003", "LLED", 2, 550);
    }

    @Test
    public void createOrderCli4() {
        createOrderThroughProcess("CLI004", "PEAR", 8, 497);

        Form.switchProcessingState("V");
        Form.save();
        Selenide.sleep(1000);
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
