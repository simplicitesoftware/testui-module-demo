package com.simplicite.menu.administration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.*;

public class Domain{

    public static void click() {
        Component.clickMenu(administration,"Domain");
    }

    public static void setHomePage(String home) {
        work.find("#field_obd_nohome_no").click();
        Component.sendFormControl(work.find("#field_obd_view_id__viw_name"), home);
        save();
    }

    public void createObject(String name) {

        SelenideElement obdname = $("#field_obd_name");
        obdname.clear();
        obdname.sendKeys(name);
    }

    public static void find(String name) {
        work.find("#obd_name").setValue(name).pressEnter();
        work.find("[data-field=\"obd_name\"]").find(new ByText(name)).click();
    }

}
