package com.simplicite.menu.administration;

import com.codeborne.selenide.SelenideElement;

import static com.simplicite.menu.MainMenuProperties.work;

public class Field {

    public static void find(String a) {
        SelenideElement field = work.find("#obf_field_id__fld_name");
        field.clear();
        field.setValue(a).pressEnter();
        work.find(".list-clickable").find(".text-left").click();
    }

    public void click() {

    }

    public void createObject() {

    }

    public void find() {

    }
}
