package com.simplicite.Simplinium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MenuInteraction {

    /** Click on a submenu in the main menu (left menu).
     *
     * @param domain Name of the domain which contains the menu
     *               (Value of data-domain in the web inspector)
     * @param name Name of the menu (Value of data-obj in the web inspector)
     */

    public static void clickMenu(String domain, String name) {
        var domainelement = $("[data-domain=\"" + domain + "\"]");

        domainelement.scrollIntoView(false);
        SelenideElement element = $("[data-obj=\"" + name + "\"]");
        while (!element.isDisplayed())
            domainelement.click();
        element.click();
    }

    /** Click on a process submenu in the main menu (left menu).
     *
     * @param domain Name of the domain which contains the menu
     *               (Value of data-domain in the web inspector)
     * @param name Name of the menu (Value of data-wkf in the web inspector)
     */
    public static void clickMenuCreateOrder(String domain, String name) {
        var domainelement = $("[data-domain=\"" + domain + "\"]");

        domainelement.scrollIntoView(false);
        SelenideElement element = $("[data-wkf=\"" + name + "\"]");
        while (!element.isDisplayed())
            domainelement.click();
        element.click();
    }

    /** Click on a state in a submenu in the main menu (left menu).
     *
     * @param domain Name of the domain which contains the menu
     *               (Value of data-domain in the web inspector)
     * @param name Name of the menu (Value of data-obj in the web inspector)
     * @param state Name of the state needed to be clicked
     */
    public static void clickMenuState(String domain, String name, String state) {
        $("[data-domain=\"" + domain + "\"]").shouldBe(Condition.visible);
        SelenideElement element = $("[data-state=\"" + state + "\"]");
        if (!element.isDisplayed())
            MenuInteraction.clickMenu(domain, name);
        element.click();
    }

    /** Click on the dropmenu (on the right).
     *
     * @param type 0 -> SocialPosts
     *             1 -> Feedback
     *             2 -> News
     *             3 -> ScriptEditor
     *             4 -> Cache
     *             5 -> Logs
     *             6 -> DBAccess
     *             7 -> Resources
     *             8 -> JavaDoc
     *             9 -> ImportCSV
     *             10 -> ImportXML
     *             11 -> About
     *             Any number -> click only on the dropmenu
     */
    public static void clickDropDownMenu(int type) {

        SelenideElement dropmenubutton = Selenide.$(".btn-header.btn-shortcut").parent();
        Selenide.actions().pause(Duration.ofSeconds(1)).moveToElement(dropmenubutton).click().perform();

        switch (type) {
            case 0 -> dropmenubutton.find(Selectors.byCssSelector("[data-shortcut=\"SocialPosts\"]")).click();
            case 1 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"Feedback\"]")).click();
            case 2 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"News\"]")).click();
            case 3 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"ScriptEditor\"]")).click();
            case 4 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"Cache\"]")).click();
            case 5 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"Logs\"]")).click();
            case 6 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"DBAccess\"]")).click();
            case 7 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"Resources\"]")).click();
            case 8 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"JavaDoc\"]")).click();
            case 9 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"ImportCSV\"]")).click();
            case 10 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"ImportXML\"]")).click();
            case 11 -> dropmenubutton.find(Selectors.byCssSelector("*[data-shortcut=\"About\"]")).click();
            default -> dropmenubutton.click();
        }
    }
}
