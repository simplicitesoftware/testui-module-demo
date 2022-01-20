package com.simplicite.menu.domaininterface;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.simplicite.utils.Component;

import static com.simplicite.menu.MainMenuProperties.*;

public class Theme {

    public static void click() {
        Component.clickMenu(domaininterface,"Theme");
    }

    public static void createTheme(String name){
        create();
        work.find("#field_thm_name").setValue(name);
        Selenide.sleep(1000);
        work.find("#select2-field_thm_base-container").parent().click();
        work.find("#select2-field_thm_base-results").find("[data-select2-id$=\"light\"]").shouldBe(Condition.visible).click();
        save();
    }
}
