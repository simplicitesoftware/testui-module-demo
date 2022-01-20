package com.simplicite.menu.templateeditor;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.next;
import static com.simplicite.menu.MainMenuProperties.work;

public class TemplateEditorBO extends TemplateEditor{

    /**
     * 3 => Short text
     * 13 => Long text
     * 1 => Integer
     * 2 => Decimal (double)
     * 26 => Decimal (100,32)
     * 4 => Date
     * 5 => Date and time
     * 6 => Time
     * 7 => Enumeration
     * 14 => Multiple enumeration
     * 8 => Boolean
     * 10 => URL
     * 11 => HTML content
     * 12 => Email
     * 15 => Validated text
     * 17 => Document
     * 0 => Internal ID
     * 24 => Object
     * 9 => Password
     * 19 => External file
     * 20 => Image
     * 21 => Notepad
     * 22 => Phone number
     * 23 => Color
     * 25 => Geographical coordinates
     *
     * @param name          name
     * @param label         label
     * @param type          type
     * @param required      required
     * @param functionalkey fonctionnalkey
     */
    public static void addField(String areaname, String name, String label, int type, boolean required, boolean functionalkey) {

        addButton(areaname, "field");
        SelenideElement typed = $("#dlgmodal_field").find("#typed");
        typed.click();

        typed.find("[data-field-type=\"" + type + "\"]").click();

        //event opening dlgmodal
        Selenide.sleep(2000);

        SelenideElement modal = $("#dlgmodal");
        modal.find("#label").setValue(name);
        modal.find("#name").setValue(label);
        if (functionalkey)
            modal.find("label[for=\"key\"]").click();
        if (required)
            modal.find("label[for=\"req\"]").click();
        Component.clickOnButtonEndDlgmodal("button[data-action=\"SAVE\"]");
    }

    public static void addFieldUnusedJoin(String areaname, String join, String field) {
        addButton(areaname, "field");

        SelenideElement type = $("#dlgmodal_field").find("#" + join);
        type.click();
        type.find("[data-field=\"" + field + "\"]").click();

    }

    public static void editEnum(String... args) {
        if (args.length < 3)
            throw new IllegalArgumentException();

        $("#dlgmodal").find("#editlist").click();

        SelenideElement dlgedit = $("#dlgmodal_editlist");
        int count = 0;
        for (var arg : args) {
            if (count < 3) {
                String str = Character.toString('A' + count);
                dlgedit.find("[name=\"code\"][value=\"" + str + "\"]").setValue(arg.toUpperCase(Locale.ROOT));
                dlgedit.find("[name=\"value\"][value=\"" + str + "\"]").setValue(arg);
            } else {
                dlgedit.find(".edit-lov > button").click();
                dlgedit.findAll(byName("code")).last().setValue(arg.toUpperCase(Locale.ROOT));
                dlgedit.findAll(byName("value")).last().setValue(arg);
            }
            count++;
        }

        SelenideElement save = dlgedit.find("[data-action=\"SAVE\"]");
        actions().moveToElement(save).click().perform();
    }

    public static void addStateModel(int[][] listaccessorderstate, String group, String[][] trad) {
        work.find("button[data-action=\"OBJ_ADD_STM\"]").click();
        next();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (listaccessorderstate[y][x] == 1)
                    work.find("input[name=\"chk" +  y + "." + x + "\"]").click();
            }
        }
        next();
        ElementsCollection list = work.find(byText(group)).parent().findAll("input[type=\"checkbox\"]").filterBy(visible);
        list.forEach(SelenideElement::click);
        next();
        Arrays.stream(trad).forEach(e -> work.find("[name*=\"EN\"][value=\"" + e[0] + "\"]").setValue(e[1]));
        next();
    }

    public static void accessToOption(){
        SelenideElement area = work.find("[data-areaname=\"ObjectInternal-Btn\"]");
        area.find("[data-tab=\"1\"]").click();
        area.find("#field_obo_historic_2").click();
    }

    public static void saveEditor() {
        $("button[data-action=\"save\"]").shouldBe(and("Clickable", visible, enabled)).click();
        SelenideElement element = $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]").shouldBe(and("Clickable", visible, enabled));
        actions().moveToElement(element).pause(Duration.ofSeconds(1)).click(element).pause(Duration.ofSeconds(1)).perform();
        $("#dlgmodal_saveAll").shouldNot(visible);
    }

}
