package com.simplicite.optionmenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class ModuleActive {

    public static void click(){
        $(".icon-module").click();
    }

    public static void showAll(){
        SelenideElement dlgmodal = $("#dlgmodal_modules");
        SelenideElement button = dlgmodal.find("button[data-action=\"SHOWALL\"]");
        actions().pause(Duration.ofSeconds(2)).moveToElement(button).click(button).pause(Duration.ofSeconds(2)).build().perform();
    }

    public static void select(String module){
        SelenideElement dlgmodal = $("#dlgmodal_modules");
        SelenideElement element = dlgmodal.findAll(".module").filterBy(Condition.text(module)).first();
        actions().moveToElement(element).click().perform();
        SelenideElement save = dlgmodal.find("button[data-action=\"SAVE\"]");
        actions().moveToElement(save).click().pause(1).build().perform();
    }
}
