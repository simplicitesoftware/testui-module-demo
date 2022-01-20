package com.simplicite.menu.templateeditor;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.work;

public class TemplateEditorView extends TemplateEditor {

    //problem de nommage Save -> save
    public static void saveEditor() {
        work.find(".btn-save").shouldBe(and("Clickable", visible, enabled)).click();
        SelenideElement element = $("#dlgmodal_saveAll").find("button[data-action=\"SAVE\"]").shouldBe(and("Clickable", visible, enabled));
        actions().moveToElement(element).pause(1).click(element).perform();
    }


    /**
     * @param type L -> Login
     *             D -> Date
     *             T -> Time
     *             S -> Preset search
     *             C -> Text code
     *             I -> Image
     *             P -> Child list
     *             X -> Crosstable
     *             Z -> Publication template
     *             N -> Search by index
     *             E -> External page
     *             W -> Web news
     *             U -> Shortcuts
     */

    public static void setArea(String type) {
        $("#dlgmodal").find("#type").selectOptionByValue(type);
    }

    public static void setProcessSearch(String name, String object){
        setArea("S");
        SelenideElement dlgmodal = $("#dlgmodal");
        dlgmodal.find("#sname").setValue(name);
        dlgmodal.find("#sobj").setValue(object);
        
        saveDlgmodal();
    }

    public static void setCrossTable(String name, String object){
        setArea("X");
        SelenideElement dlgmodal = $("#dlgmodal");
        dlgmodal.find("#ctname").setValue(name);
        dlgmodal.find("#ctobj").setValue(object);

        dlgmodal.find("[for=\"ztable\"]").parent().find(".slider").click();
        saveDlgmodal();
    }

    public static void addField() {
        addButton(null, "item-S");
    }

    //don't have data value
    public static void setProcessSearchFilter(String field, String value) {
        SelenideElement dlgmodal = $("#dlgmodal");
        dlgmodal.find(".add-filter").click();
        dlgmodal.find(".sfield").selectOptionByValue(field);
        dlgmodal.find(".sval").setValue(value);

        saveDlgmodal();
    }
}
