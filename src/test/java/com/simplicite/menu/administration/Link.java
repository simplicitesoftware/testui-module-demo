package com.simplicite.menu.administration;

import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;

import static com.codeborne.selenide.Selenide.$;
import static com.simplicite.menu.MainMenuProperties.work;

public class Link {

    public static void addLink(String from, String to, String...fieldstolink)
    {
        BusinessObject.click();
        BusinessObject.find(from);
        work.find("button[data-action=\"OBJ_ADD_LINK\"]").click();
        MainMenuProperties.next();
        work.find("#obo_name").setValue(to).pressEnter();
        work.find(".list-clickable").find(".text-left").click();
        MainMenuProperties.next();
        work.find("#field_obf_card").setValue("1,n");
        work.find("#select2-field_obf_cascad-container").click();
        work.find(".select2-results").find("[id$=\"C\"]").click();
        work.find("#field_obf_field_id__fld_required").click();
        MainMenuProperties.next();
        SelenideElement container = $(".container-table");

        for (var a: fieldstolink) {
            Field.find(a);
        }

        MainMenuProperties.next();
        MainMenuProperties.next();
    }
}
