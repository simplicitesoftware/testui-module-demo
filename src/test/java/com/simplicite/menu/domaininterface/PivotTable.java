package com.simplicite.menu.domaininterface;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.*;

public class PivotTable {

    public static void click() {
        Component.clickMenu(domaininterface,"Crosstab");
    }

    public static void createPivotTable(String name, String code, String module){

        work.find("button[data-action=\"create\"]").click();

        work.find("#field_ctb_name").setValue(name);
        Component.sendFormControl( work.find("#field_ctb_object_id__obo_name"), code);

        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), module);

        work.find("#select2-field_ctb_function-container").parent().click();
        work.find("#select2-field_ctb_function-results").find("[data-select2-id$=\"S\"]").shouldBe(Condition.visible).click();
        work.find("#field_ctb_grantable_no").click();

        save();
    }

    /**
     *
     * @param object object
     * @param field field
     * @param type C for Column, L for Line, V for Value
     */
    public static void createPivotAxis(String object, String field, char type, int axisorder)
    {
        work.find("[data-key=\"CrosstabAxis_cax_crosstab_id\"]").click();
        work.find("#list_CrosstabAxis_panel_ajax_CrosstabAxis_cax_crosstab_id").find("button[data-action=\"create\"]").click();

        work.find("[data-action=\"refsel_field_cax_objfield_id__obf_object_id__obo_name\"]").click();

        $("#obf_object_id__obo_name").setValue(object).pressEnter();
        $("#obf_field_id__fld_name").setValue(field).pressEnter();
        SelenideElement obj_name = $("[data-field=\"obf_object_id__obo_name\"]").shouldHave(Condition.text(object));
        actions().moveToElement(obj_name).click().perform();

        work.find("#select2-field_cax_type-container").parent().click();
        work.find("#select2-field_cax_type-results").find("[data-select2-id$=\"" +  type+ "\"]").click();

        work.find("#field_cax_order").setValue(String.valueOf(axisorder));
        save();
        close();
    }
    public static void find(String name)
    {
        work.find("#act_name").setValue(name).pressEnter();
        work.find("[data-field=\"act_name\"]").shouldHave(Condition.textCaseSensitive(name)).click();
    }
}
