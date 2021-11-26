package com.simplicite.Simplinium;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class Process {

    /** Click the button close
     *
     */
    public static void nextPage() {
        $("button[data-action=\"validate\"]").click();
        //depends of the performance can be remove
        Selenide.sleep(200);
    }

    /** Click the button create
     *
     */
    public static void create() {
        $("button[data-action=\"create\"]").click();
    }

    /** Switch the processing state to a certain state.
     *
     * @param state The state to switch
     */
    public static void switchProcessingState(String state) {
        Selenide.$("button[data-state=\"" + state + "\"]").click();
    }

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
