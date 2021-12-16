package com.simplicite.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Authentication {



    /**
     * Connect the user
     **/
    public static void connect(String usr, String password) {
        $("#auth-signin-username").setValue(usr);
        $("#auth-signin-password").setValue(password);
        $("#auth-signin-submit").click();
    }

    /**
     * Test if the authentification succed if a pop-up error appear return false
     */
    public static boolean authentificationSucced(String usr) {
        SelenideElement element = $("span.user-name");
        element.should(Condition.exist);
        element.shouldHave(Condition.text(usr));
        return true;
    }

    public static void deconnection() {
        $(".logged-user").click();
        $(".user-logout").click();
        $("#dlgmodal_CONFIRM_LOGOUT").find("button[data-action=\"OK\"]").click();
    }

    public static void changePassword(String newPassword) {
        if ($("#auth-signin-password1").exists()) {
            $("#auth-signin-password1").setValue(newPassword);
            $("#auth-signin-password2").setValue(newPassword);
            $("#auth-signin-save").click();
        }

    }

    public static void changeState(String state, String codeImg) {
        $(".logged-scope").click();
        $(".logged-scope").find("[data-home=\""+ state + "\"]").click();
        $(".scope-icon > img[src*=\""+ codeImg + "\"]").shouldBe(Condition.exist, Duration.ofSeconds(20));

    }

    public static boolean isAuthentificationPage() {
        return $("#auth-main").exists();
    }
}
