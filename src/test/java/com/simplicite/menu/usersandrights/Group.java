package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.domain;
import static com.simplicite.menu.MainMenuProperties.work;

public class Group {

    public void click() {
        Component.clickMenu(domain,"Group");
    }

    public static void createObject(String name) {
        SelenideElement grpname = $("#field_grp_name");
        grpname.clear();
        grpname.sendKeys(name);
    }

    public static void find(String name) {

        work.find("#grp_name").setValue(name).pressEnter();
        work.find("[data-field=\"grp_name\"]").find(new ByText(name)).click();

    }

}
