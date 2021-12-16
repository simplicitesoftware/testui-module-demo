package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.utils.Component;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.*;

public class User{

    public static void click() {
        Component.clickMenu(domain,"User");
        $("[data-obj=\"User\"][data-state=\"\"]").click();
    }

    public static String createUser(String name) {
        work.find("button[data-action=\"create\"]").click();
        work.find("#field_usr_login").setValue(name);
        save();
        String alert = work.find("span.msg-label").text();
        String[] split = alert.split(": ");
        String password = split[1];
        work.find("button[data-action=\"UserStatusActivate\"]").click();


        Component.clickOnButtonEndDlgmodal("button[data-action=\"YES\"]");

        return password;
    }


    public static void associateGroup(String... groups) {
        work.find("[data-action=\"associate-User-rsp_login_id-Group-rsp_group_id\"]").click();
        for (var group : groups) {
            $("#grp_name").setValue(group);
            $("[data-list=\"list_Group_ref_ajax_Group\"]").find("[data-field=\"grp_name\"]").click();
        }
        $("button[data-action=\"multiselect\"]").click();
        SelenideElement buttonsaveclose = $("#dlgmodal_create_Responsability_link_ajax_Responsability")
                .find("button[data-action=\"saveclose\"]").shouldBe(Condition.visible);
        actions().pause(Duration.ofSeconds(1)).moveToElement(buttonsaveclose).click(buttonsaveclose).perform();
    }
}
