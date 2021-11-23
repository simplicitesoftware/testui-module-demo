package com.simplicite.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class Component {
    public static void sendFormControl(SelenideElement selenideElement, String name) {
        actions().moveToElement(selenideElement).click().sendKeys(Keys.ENTER).keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).sendKeys(name).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).perform();
    }

    public static void clickOnButtonEndDlgmodal(String component) {
        SelenideElement dlgmodal = $("#dlgmodal").shouldHave(cssClass("show"));
        Selenide.sleep(1000);
        dlgmodal.find(component).click();
    }

    public static void clickMenu(String domain, String name) {
        var domainelement = $("[data-domain=\"" + domain + "\"]");

        domainelement.scrollIntoView(false);
        SelenideElement element = $("[data-obj=\"" + name + "\"]");
        while (!element.isDisplayed())
            domainelement.click();
        element.click();
    }

    public static void clickMenuCreateOrder(String domain, String name) {
        var domainelement = $("[data-domain=\"" + domain + "\"]");

        domainelement.scrollIntoView(false);
        SelenideElement element = $("[data-wkf=\"" + name + "\"]");
        while (!element.isDisplayed())
            domainelement.click();
        element.click();
    }


    public static void close() {
        $("button[data-action=\"close\"]").click();
    }

    public static void create() {
        $("button[data-action=\"create\"]").click();
    }

    public static void next() {
        $("button[data-action=\"validate\"]").click();
        Selenide.sleep(1000);
    }

    public static void save() {
        $("button[data-action=\"save\"]").click();
    }

    public static void setQuantity(String id, int value) {
        SelenideElement slider = $("#" + id);
        for (int i = 1; i < value; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            Selenide.sleep(200);
        }
    }

    public static void find(String component, String value) {
        $("#" + component).setValue(value).pressEnter();
    }


}
