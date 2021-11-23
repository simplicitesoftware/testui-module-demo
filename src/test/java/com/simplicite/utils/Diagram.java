package com.simplicite.utils;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Diagram {
    public static boolean verifyState(String component, String state) {
        return $("#" + component).getText().equals(state);
    }

    public static void switchProcessingState(String state) {
        $("button[data-state=\"" + state + "\"]").click();
    }
}
