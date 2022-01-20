package com.simplicite.utils;

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

    public static void clickOnButtonEndDlgmodal(String component){
        SelenideElement dlgmodal = $("#dlgmodal").shouldHave(cssClass("show"));
        Selenide.sleep(1000);
        dlgmodal.find(component).click();
        Selenide.sleep(1000);
    }

    public static void clickMenu(String domain, String name) {
        var domainelement = $("[data-domain=\"" + domain + "\"]");

        domainelement.scrollIntoView(false);
        SelenideElement element = $("[data-obj=\"" + name + "\"]");
        while (!element.isDisplayed())
            domainelement.click();
        element.click();
    }
}
