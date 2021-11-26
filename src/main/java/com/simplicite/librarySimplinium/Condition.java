package com.simplicite.librarySimplinium;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class Condition {

    /** Verify the state of the component. Needed on diagrams to verify the functionality
     * in Simplicité.
     *
     * @param component ID of the component which contains the state
     * @param state Name of the state needed to be verified
     * @return a boolean true if it is in the same state that the argument
     */
    public static boolean verifyState(String component, String state) {
        return Selenide.$("#" + component).getText().equals(state);
    }

    /** Verify if the pivot table exist on the wab page. The error is thrown by Selenide
     * if the pivot table doesn't exist.
     *
     * @param name name of the pivot table (after "crosstable_" in the attribute data-action)
     */
    public static void verifyPivotTable(String name){
        $("button[data-action=\"crosstab_"+ name + "\"]").click();
        $("#work").find(".crosstab").should(com.codeborne.selenide.Condition.exist);
    }
}
