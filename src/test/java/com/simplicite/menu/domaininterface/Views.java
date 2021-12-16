package com.simplicite.menu.domaininterface;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.*;

public class Views {

    public static void click() {
        Component.clickMenu(domaininterface,"View");
    }

    public static void createView(String name, String modulename, int order){
        create();

        work.find("#field_viw_name").setValue(name);
        work.find("#field_viw_order").setValue(String.valueOf(order));
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), modulename);

        save();
    }

    public static void find(String name) {
        work.find("#viw_name").setValue(name);
        work.find("[data-field=\"viw_name\"]").click();
    }

    public static void setTheme(String name){
        work.find("[data-tab=\"1\"]").click();
        work.find("#field_viw_theme_id__thm_name").click();
        $("#dlgmodal_selectRef_Theme_viw_theme_id").find("#thm_name").setValue(name);
        SelenideElement element = $("#dlgmodal_selectRef_Theme_viw_theme_id").find("[data-field=\"thm_name\"]");
        actions().pause(Duration.ofSeconds(1)).moveToElement(element).click().perform();
        work.find("[data-tab=\"0\"]").click();
        save();
    }
}
