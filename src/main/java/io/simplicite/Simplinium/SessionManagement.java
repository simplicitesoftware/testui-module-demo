package io.simplicite.Simplinium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class SessionManagement {

    /** Connect the user. If it is the first time,
     * change the user password to the same for the connection.
     *
     * @param usr Name of the user needed to be connected
     * @param password String of the password of the user
     */
    public static void connect(String usr, String password) {
        Selenide.$("#auth-signin-username").setValue(usr);
        Selenide.$("#auth-signin-password").setValue(password);
        Selenide.$("#auth-signin-submit").click();
        changePassword(password);
    }


    /** Test if the authentification succed if a pop-up error appear and the page
     * didn't change return false.
     *
     * @param usr Name of the user connected
     * @return boolean true if authentication succeed false if not
     */
    public static boolean authenticationSucceed(String usr) {
        SelenideElement element = Selenide.$("span.user-name");
        element.should(Condition.exist);
        element.shouldHave(Condition.text(usr));
        return true;
    }

    /**
     * Disconnect the user.
     */
    public static void disconnection() {
        Selenide.$(".logged-user").click();
        Selenide.$(".user-logout").click();
        Selenide.$("#dlgmodal_CONFIRM_LOGOUT").find("button[data-action=\"OK\"]").click();
    }

    /** Change the password for the first connection.
     *
     * @param newPassword String containing the new password
     */
    public static void changePassword(String newPassword) {
        if (Selenide.$("#auth-signin-password1").exists()) {
            Selenide.$("#auth-signin-password1").setValue(newPassword);
            Selenide.$("#auth-signin-password2").setValue(newPassword);
            Selenide.$("#auth-signin-save").click();
        }

    }

    /** Change the scoop of the session
     *
     * @param state Value of the state needed to be accessed
     * @param codeImg Value of the IMG
     */
    public static void changeScope(String state, String codeImg) {
        Selenide.$(".logged-scope").click();
        Selenide.$(".logged-scope").find("[data-home=\""+ state + "\"]").click();
        Selenide.$(".scope-icon > img[src*=\""+ codeImg + "\"]").shouldBe(Condition.exist, Duration.ofSeconds(20));

    }

    /** Verify if it is the authentification page
     *
     * @return true if it is false if it is not
     */
    public static boolean isAuthentificationPage() {
        return Selenide.$("#auth-main").exists();
    }

    /** Clear the cache of the session. Needed to be on the cache page
     *
     * @param type u -> Clear your cache only
     *             s -> Clear all caches on server
     *             c -> Clear all sessions and all server caches
     */
    public static void clearCache(char type) {
        if (type == 'u')
            Selenide.$("*[src$=\"cc1.png\"]").click();

        else if (type == 's')
            Selenide.$("*[src$=\"cc2.png\"]").click();

        else if (type == 'c') {
            Selenide.$("*[src$=\"cc3.png\"]").click();
            if (Selenide.$(".btn-OK[data-action=\"OK\"]").exists())
                Selenide.$(".btn-OK[data-action=\"OK\"]").click();
        }
    }
}
