package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.simplicite.menu.MainMenuProperties.*;

public class ListOfValue {

    public static void click() {
        Component.clickMenu(administration,"FieldList");
    }

    public static void find(String name) {
        work.find("#lov_name").setValue(name).pressEnter();
        work.find("[data-field=\"lov_name\"]").click();
    }

    public static void modifyListCodeIcon(String code, String icon) {
        work.find("[data-key=\"FieldListCode_lov_list_id\"]").click();
        SelenideElement panel = work.find("[data-object=\"FieldListCode\"]");
        panel.findAll("[data-field=\"lov_code\"]").filter(Condition.text(code)).first().click();
        work.find("#field_lov_icon").sendKeys(icon);
        saveAndClose();
    }

}
