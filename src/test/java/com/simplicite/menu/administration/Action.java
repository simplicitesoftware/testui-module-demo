package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.*;

public class Action {

    public static void click() {
        Component.clickMenu(administration,"Action");
    }

    public static void createAction(String name, String module,String url){
        work.find("button[data-action=\"create\"]").click();
        work.find("#field_act_name").setValue(name);

        work.find("#select2-field_act_type-container").parent().click();
        work.find(".select2-results").find("[id$=\"O\"]").shouldBe(Condition.visible).click();

        work.find("#select2-field_act_exec-container").parent().click();
        work.find(".select2-results").find("[id$=\"FRT\"]").shouldBe(Condition.visible).click();

        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), module);
        work.find("#field_act_url").setValue(url);
        save();
    }

    public static void addFunction(String target, String fct_name)
    {
        work.find("#linktab_Function_fct_action_id").click();
        work.find("#view_Function_fct_action_id").find("button[data-action=\"create\"]").click();

        work.find("#field_fct_name").setValue(fct_name);
        work.find("[data-action=\"refsel_field_fct_object_id__obj_name\" ]").click();

        $("#obj_name").setValue(target).pressEnter();
        SelenideElement obj_name = $("[data-field=\"obj_name\"]").shouldHave(Condition.text(target));
        actions().moveToElement(obj_name).click().perform();

        save();
    }
    public static void find(String name)
    {
        work.find("#act_name").setValue(name).pressEnter();
        work.find("[data-field=\"act_name\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }
}
