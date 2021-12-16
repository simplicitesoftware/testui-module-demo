package com.simplicite.menu.templateeditor;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.work;
import static java.time.Duration.ofSeconds;

public class TemplateEditor {

    public static void navigateToEditor() {
        $("button[data-action*=\"editTemplate\"]").shouldBe(Condition.exist, ofSeconds(20)).click();
        SelenideElement element = $("#dlgmodal").find(".img-flow > img:nth-child(1)").shouldBe(Condition.appear);
        actions().pause(ofSeconds(1)).moveToElement(element).click(element).perform();
        Selenide.sleep(1000);
    }

    public static void addButton(String areaname, String type) {
        SelenideElement zone;
        if (areaname == null)
            zone = work.find(".dock-zone[data-dz=\"1\"]").should(Condition.exist);
        else {
            // card-body not needed but add because of a bug in template editor last is not last, dock-zone out of card-body
            SelenideElement area = work.find("[data-areaname=\"" + areaname + "\"]").should(Condition.exist).find(".card-body");
            zone = area.findAll(".dock-zone").last().should(visible);

        }
        zone.scrollIntoView(true);
        zone.hover();
        Selenide.executeJavaScript("arguments[0].classList.add(\"on\")", zone);
        SelenideElement button = $(".dock-zone.on").find(".btn-insert").should(exist);
        button.click();
        SelenideElement element = $(".dock-zone.on").find("[data-menu=\"" + type + "\"]")
                .shouldBe(and("exist and visible", exist, visible));
        element.click();
    }

    public static void addRow() {
        SelenideElement area1 = work.find(".dock-zone[data-dz=\"1\"]");
        area1.find("button").click();
        area1.find("[data-menu=\"rowcols row2\"]").click();
    }

    public static void addArea() {
        addButton(null, "area");

    }

    public static void clickEditor() {
        $("button[data-action*=\"editTemplate\"]").click();
        Selenide.sleep(2000);
    }


    public static void setEditorTemplate(String html) {
        $("button[data-action=\"save\"]").click();
        SelenideElement dlgmodal = $("#dlgmodal_saveAll");
        SelenideElement text = dlgmodal.find("textarea").shouldBe(Condition.visible);
        Selenide.sleep(1000);
        text.clear();
        actions().moveToElement(text).click(text).sendKeys(html).build().perform();
        SelenideElement element = dlgmodal.find("button[data-action=\"SAVE\"]");
        actions().moveToElement(element).click(element).perform();
    }

    public static void modifyArea(String area) {
        SelenideElement edit = work.find(".area[data-area=\"" + area + "\"]").find("[data-action=\"edit\"]");
        edit.hover().click();
        $("#dlgmodal").shouldBe(cssClass("show"));
    }

    public static void modifyField(String field) {
        SelenideElement edit = work.find("[data-field=\"" + field + "\"]").find("[data-action=\"edit\"]");
        edit.hover().click();
        $("#dlgmodal").shouldBe(cssClass("show"));

        Selenide.sleep(1000);
    }

    public static void closeEditor() {
        MainMenuProperties.close();
    }

    public static void saveDlgmodal() {
        Component.clickOnButtonEndDlgmodal("[data-action=\"SAVE\"]");
    }
}
