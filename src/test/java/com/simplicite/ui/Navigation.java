package com.simplicite.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Navigation {



    public static void clickState(String domain, String name, String state) {
        $("[data-domain=\"" + domain + "\"]").shouldBe(Condition.visible);
        SelenideElement element = $("[data-state=\"" + state + "\"]");
        if (!element.isDisplayed())
            Component.clickMenu(domain, name);
        element.click();
    }

    public static void search(String id, String idtext) {
        $$("[data-field=\"" + id+ "\"]").filter(Condition.text(idtext)).first().click();
    }
}
