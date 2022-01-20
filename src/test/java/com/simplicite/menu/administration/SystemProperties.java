package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.simplicite.utils.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.administration;
import static com.simplicite.menu.MainMenuProperties.work;

public class SystemProperties {

    public static void find(String name) {
        work.find("#sys_code").setValue(name).pressEnter();
        work.find("[data-field=\"sys_code\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static void click() {
        Component.clickMenu(administration,"SystemParam");
    }

    public static boolean verifyValue() {
        String txt = $("#field_sys_value_edit").getText();
        Pattern p = Pattern.compile(".*\"database\": true.*");
        Matcher m = p.matcher(txt);
        return m.find();
    }
}
