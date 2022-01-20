package com.simplicite.menu.administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.*;

public class Module {

    public static void createModuleAssistant(String mdl_name, String mdl_prefix, String grp_name, String dmn_name, String icone) {
        SelenideElement createassistant = work.find("*[data-action=\"ModuleCreateProcess\"]");
        createassistant.click();

        SelenideElement fieldname = $("#field_mdl_name").should(Condition.exist);
        fieldname.clear();
        fieldname.sendKeys(mdl_name);

        SelenideElement fieldprefix = $("#field_mdl_prefix");
        fieldprefix.clear();
        fieldprefix.sendKeys(mdl_prefix);

        next();

        SelenideElement grpname = $("#field_grp_name");
        grpname.clear();
        grpname.sendKeys(grp_name);
        next();

        SelenideElement obdname = $("#field_obd_name");
        obdname.clear();
        obdname.sendKeys(dmn_name);
        next();
        next();

        SelenideElement table = work.find(".table");
        SelenideElement line = table.find(byText(grp_name)).parent();
        SelenideElement checkbox = line.find(byCssSelector("input[type=\"checkbox\"]"));
        checkbox.click();

        next();

        SelenideElement search = work.find("*[data-action=\"dmsel_field_viw_icon\"][data-toggle=\"tooltip\"]");

        work.find("#field_viw_icon").setValue(icone);
        next();
    }

    public static void click() {
        Component.clickMenu(administration,"Module");
    }

    /*public void createObject(String name, String prefix) {

        SelenideElement fieldname = $("#field_mdl_name");
        fieldname.clear();
        fieldname.sendKeys(name);

        SelenideElement fieldprefix = $("#field_mdl_prefix");
        fieldprefix.clear();
        fieldprefix.sendKeys(prefix);
    }*/

    public static void find(String name) {

        work.find("#mdl_name").setValue(name).pressEnter();
        work.find("[data-field=\"mdl_name\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }

    public static void delete(String name) {
        find(name);
        work.find("div.card > div.card-header > div > div.form-actionbar > div > div.dropdown > button.btn-plus").click();
        work.find("div.card > div.card-header > div > div.form-actionbar > div " +
                "> div.dropdown.show > ul > li[data-action=\"ModuleDelete\"]").click();

        //Bad access with the css selector need to click two times
        Component.clickOnButtonEndDlgmodal("button[data-action=\"YES\"]");

        work.find("button.btn-primary").shouldBe(Condition.visible).click();
    }

    public static boolean isSuccess(String name) {

        work.find("#field_mdl_name").should(Condition.exist).shouldHave(Condition.value(name));
        return true;
    }
}
