package com.simplicite.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.simplicite.simplinium.Process;
import io.simplicite.simplinium.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

@ExtendWith({ScreenShooterExtension.class})
public class DemoUiTest {

    @BeforeAll
    public static void setUpAll() {
        Config.url = "https://ggally.demo.simplicite.io/";
        Config.password = "designer1903";
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


    @Test
    public void createOrderCli1() {
        createOrderThroughProcess("CLI001", "BIM", 5, 850);
    }

    @Test
    public void createOrderCli2() {
        createOrderThroughProcess("CLI002", "DY", 3, 582);
    }

    @Test
    public void createOrderCli3() {
        createOrderThroughProcess("CLI003", "LLED", 6, 550);
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
        Form.setSliderValue("field_demoOrdQuantity", 1, quantity);
        Process.nextPage();

        String expectedTotal = Integer.toString(quantity * expectedPrice);
        String foundTotal = Objects.requireNonNull($("#field_demoOrdTotal").getValue())
                .replaceAll("[,]", "")
                .replaceAll("[.][0-9]*", "");
        Assertions.assertEquals(expectedTotal, foundTotal);
        Form.save();
    }
}
