package com.simplicite.menu;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Create a base for the menu, default action useful to every item in the menu
 **/
public class MainMenuProperties {

    /**
     * Element needed to accesto the menu and to restrain the search of new elements
     */
    public static SelenideElement mainmenu = $("#menu");
    public static SelenideElement work = $("#work");
    public final static String administration = "DomainAdmin";
    public final static String domain = "DomainGrant";
    public final static String domaininterface = "DomainInterface";

    /**
     * Save the component in the menu of creation
     */
    public static void save() {
        $("button[data-action=\"save\"]").click();
        Selenide.sleep(500);
    }

    /**
     * Save an close the component in the menu of creation
     */
    public static void saveAndClose() {
        $("button[data-action=\"saveclose\"]").click();
    }


    public static void next() {
        work.find("button[data-action=\"validate\"]").click();
        Selenide.sleep(1000);
    }

    /**
     * Delete the component of the programme
     */
    static void delete(String name) {
        work.find("div.card > div.card-header > div > div.form-actionbar > div > div > button").click();
        SelenideElement delete = work.find("div.card > div.card-header > div > div.form-actionbar > div " +
                "> div.dropdown.show > ul > li[data-action=\"delete\"]");
    }

    public static void close() {
        $("button[data-action=\"close\"]").click();
    }

    public static void create() {
        work.find("button[data-action=\"create\"]").click();
    }
}
