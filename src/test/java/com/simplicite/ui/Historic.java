package com.simplicite.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Historic {

    public static void verifyHistoric(String user, String object,String fieldtochange, String value){
        SelenideElement field = $("#" + fieldtochange);
        String updated = field.getText();
        field.setValue(value);
        MainMenuProperties.save();

        SelenideElement redolog = $("[data-object=\"RedoLog\"]").should(Condition.exist);

        SelenideElement element = redolog.findAll("[data-target=\"RedoLog\"]").first().find("[data-field=\"rlg_html\"]");
        element.find(".object").shouldHave(Condition.text(object));
        element.find(".user").shouldHave(Condition.text(user));

        String txt = element.find(".table").getText();
        Pattern p = Pattern.compile(".*" + updated + ".*" + value);
        Matcher m = p.matcher(txt);
        assertTrue(m.find());
    }
}
