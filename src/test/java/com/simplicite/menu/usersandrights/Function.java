package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.domain;
import static com.simplicite.menu.MainMenuProperties.work;

public class Function {


    public static void find(String name) {
        work.find("#fct_name").setValue(name).pressEnter();
        work.find("[data-field=\"fct_name\"]").find(byText(name)).click();
    }

    public static void setModuleName(String mdl_name) {
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), mdl_name);
    }

    /**
     * READ | CREATE | UPDATE | DELETE | ACTION | VIEW
     * L : READ
     * C : READ / CREATE
     * M : READ / UPDATE
     * S : READ / DELETE
     * CM : READ / CREATE / UPDATE
     * CS : READ / CREATE / DELETE
     * MS : READ / UPDATE / DELETE
     * CMS : READ / CREATE / UPDATE / DELETE
     * A   : ACTION
     * V   : VIEW
     */

    private String addSuffix(String str, String suf) {
        String suffix;
        switch (suf) {
            case "CMS" -> suffix = "CRUD";
            case "MS" -> suffix = "RUD";
            case "CS" -> suffix = "CRD";
            case "CM" -> suffix = "CRU";
            case "L" -> suffix = "R";
            case "C" -> suffix = "CR";
            case "M" -> suffix = "RU";
            case "S" -> suffix = "RD";
            default -> suffix = suf;
        }
        return str + "_" + suffix;
    }

    public static void click() {
        Component.clickMenu(domain,"Function");
    }

    public static void createObject(String mdl_name, String field_fct_name, String field_fct_function) {
        work.find("#field_fct_name").setValue(field_fct_name);
        work.find("#select2-field_fct_function-container").click();
        work.find("#select2-field_fct_function-results").find("[id$=\"" + field_fct_function + "\"]").click();
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), mdl_name);
    }

    public static void addGrant(String fct_name, String grp_name, String mdl_name) {
        Grant.click();
        Grant.createObject(grp_name, mdl_name, fct_name);
    }

    public static void associateGroup( String... groups) {
        work.find(".objlinks").find("[data-action=\"associate-Function-grt_function_id-Group-grt_group_id\"]").click();
        SelenideElement dlgmodal = $("#dlgmodal_selectObj_Group");
        for (String group : groups) {
            dlgmodal.find("#grp_name").setValue(group).pressEnter();
            dlgmodal.find("[data-field=\"grp_name\"]").shouldHave(Condition.textCaseSensitive(group)).click();
        }

        actions().moveToElement(dlgmodal.find("button[data-action=\"multiselect\"]")).click().perform();
        SelenideElement saveclose = $("#dlgmodal_create_Grant_link_ajax_Grant")
                .find("button[data-action=\"saveclose\"]").shouldBe(Condition.visible);
        actions().pause(Duration.ofSeconds(1)).moveToElement(saveclose).click().pause(Duration.ofSeconds(1)).perform();
    }

    public void find(String field_fct_function, String field_fct_name) {
        work.find("#fct_name").setValue(field_fct_function).pressEnter();
        work.find("[data-field=\"fct_name\"]").shouldHave(Condition.textCaseSensitive(field_fct_name)).click();
    }
}
