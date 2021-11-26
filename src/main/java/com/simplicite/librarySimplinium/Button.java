package com.simplicite.librarySimplinium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Button {

    /** Click the button close
     *
     */
    public static void close() {
        $("button[data-action=\"close\"]").click();
    }

    /** Click the button create
     *
     */
    public static void create() {
        $("button[data-action=\"create\"]").click();
    }

    /** Click the button close
     *
     */
    public static void next() {
        $("button[data-action=\"validate\"]").click();
        //depends of the performance can be remove
        Selenide.sleep(200);
    }

    /** Click the button save
     *
     */
    public static void save() {
        $("button[data-action=\"save\"]").click();
    }

    /** Click the button save&close
     *
     */
    public static void saveAndClose() {
        $("button[data-action=\"saveclose\"]").click();
    }

    public static void clickOnButtonEndDlgmodal(String component) {
        SelenideElement dlgmodal = $("#dlgmodal").shouldHave(Condition.cssClass("show"));
        Selenide.sleep(1000);
        dlgmodal.find(component).click();
    }

}
