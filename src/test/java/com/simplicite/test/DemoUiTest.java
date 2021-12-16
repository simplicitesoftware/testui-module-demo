package com.simplicite.test;

import com.codeborne.selenide.*;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.simplicite.Simplinium.Form;
import io.simplicite.Simplinium.General;
import io.simplicite.Simplinium.List;
import io.simplicite.Simplinium.SessionManagement;
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
import java.util.Properties;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
            System.out.println("[DEMO] Browser log created: " + System.getProperty("user.dir") + "/build/reports/tests/com/browser.log");
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
    public void triColonne() {
        General.clickMenu(Datastore.DOMAIN, Datastore.BOBJECT1);

        $("button[data-action=\"search\"]").click();
        Selenide.sleep(1000);
        SelenideElement dlgmodal = $("#dlgmodal_search");
        dlgmodal.find("#demoSupCode").setValue("*");
        dlgmodal.find("button[data-action=\"search\"]").click();
        dlgmodal.find("button[data-action=\"close\"]").click();

        $$("[data-field=\"demoSupCode\"]").forEach(e -> Assertions.assertEquals(e.getText(), "BIM"));
        //cas simple sans form controle
        //cas complexe form controle uniformisation ou bete et méchant
    }

    @Test
    public void createOnList() {
        General.clickMenu("AlcDomain", "AlcSupplier");
        $("button[data-action=\"addlist\"]").click();
        SelenideElement area = $("[data-target-id=\"0\"]");
        area.find("[data-field=\"alcSupCode\"]").find("input").setValue("TEST");
        area.find("[data-field=\"alcSupNom\"]").find("input").setValue("TESTI");
        area.find("[data-field=\"alcSupTelephone\"]").find("input").setValue("065874956");
        area.find("[data-field=\"alcSupSite\"]").find("input").setValue("http://localhost/ui").pressEnter();
        //  or save
        Form.close();
    }

    @Test
    public void deleteOnList() {
        General.clickMenu("AlcDomain", "AlcSupplier");

        //edit
        $("button[data-action=\"listedit\"]").click();
        SelenideElement area = $("[data-target-id=\"4\"]");
        area.find("[data-field=\"alcSupCode\"]").find("input").setValue("TEST2");
        List.save();

        // delete
        area.find(".actions").find("[type=\"checkbox\"]").click();
        $("#work").find(".head").find(".dropdown").click();
        $("#work").find(".head").find("[data-action=\"delall\"]").click();
        $("#dlgmodal").find("[data-action=\"OK\"]").shouldBe(Condition.visible).click();
    }

    @Test
    public void preferenceOnList() {
        General.clickMenu("AlcDomain", "AlcSupplier");

        $("#work").find(".head").find(".dropdown").click();
        $("#work").find(".head").find("[data-action=\"prefs\"]").click();
        SelenideElement dlg = $("#dlgmodal_prefs");
        dlg.find("[data-tab=\"2\"]").shouldHave(Condition.text("Action")).click();
        SelenideElement actionhide = dlg.find("#actionhid");
        SelenideElement actionvis = dlg.find("#actionvis");
        actionhide.find("[value=\"reload\"]").click();

        // 0 right arrow 1 left arrow 2 up arrow 3 down arrow
        ElementsCollection a = dlg.findAll("img.button").filter(Condition.visible);
        a.get(1).click();
        dlg.findAll("button").findBy(Condition.text("Save")).click();
    }

    @Test
    public void viewOnList() {
        General.clickMenu("AlcDomain", "AlcSupplier");

        $("#work").find(".btn-minifiable").click();
    }

    @Test
    public void paginationOnList() {
        General.clickMenu("AlcDomain", "AlcSupplier");

        System.out.println($("#work").find(".card-footer").find(".list-count").getText());
        System.out.println($("#work").find(".card-footer").find(".list-pages").getText());
        System.out.println($("#work").find(".card-footer").find(".page-item").getText());
        System.out.println($("#work").find(".card-footer").find(".list-rows").find("select").getSelectedOption().getValue());
    }
}
