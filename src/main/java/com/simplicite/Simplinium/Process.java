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

}
