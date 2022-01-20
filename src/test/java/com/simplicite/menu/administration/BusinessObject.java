package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.*;

public class BusinessObject {

    public SelenideElement objectbutton = mainmenu.find("[data-obj=\"ObjectInternal\"]");

    public static void click() {
        Component.clickMenu(administration,"ObjectInternal");
    }

    public static void createObjectAssistant(String obj_name, String obj_table, String mdl_name, String prefix, String dmn_name) {

        work.find("button[data-action=\"ObjectCreateProcess\"]").click();


        work.find("#field_obo_name").setValue(obj_name);
        work.find("#field_obo_dbtable").setValue(obj_table);
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), mdl_name);
        work.find("#field_obo_prefix").setValue(prefix);

        next();
        next();
        next();

        work.find("#obd_name").setValue(dmn_name).pressEnter();
        work.find("#obd_nohome").selectOptionByValue("true");
        work.find(".text-left").shouldHave(Condition.exactTextCaseSensitive(dmn_name)).click();
        next();
        if (work.find("button[data-action=\"validate\"]").exists())
            next();
    }

    public static void find(String name) {
        $("#obo_name").setValue(name).pressEnter();
        $("[data-field=\"obo_name\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static boolean isSuccess(String name) {
        return work.find("#field_obo_name").shouldHave(Condition.value(name)).exists();
    }

}
