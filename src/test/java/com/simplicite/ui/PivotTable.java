package com.simplicite.ui;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class PivotTable {

    public static void verifyPivotTable(String name){
        $("button[data-action=\"crosstab_"+ name + "\"]").click();
        $("#work").find(".crosstab").should(Condition.exist);
    }
}
