package com.simplicite.Simplinium;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Research {

    /** Search in the list of a business object, the object needed with a field
     *
     * @param field Field used for the search
     * @param text Text cotaining the data for the search
     */
    public static void search(String field, String text) {
        $$("[data-field=\"" + field+ "\"]").filter(Condition.text(text)).first().click();
    }

    /** Find a value in a list which chose the first element of the result of the search.
     *
     * @param component ID of the component
     * @param value Value needed to be set for the search
     */
    public static void find(String component, String value) {
        $("#" + component).setValue(value).pressEnter();
    }

}
