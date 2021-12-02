package io.simplicite.Simplinium;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class Process {

    /** Click the button close
     *
     */
    public static void nextPage() {
        $("button[data-action=\"validate\"]").click();
        //depends of the performance can be remove
        Selenide.sleep(500);
    }



}
