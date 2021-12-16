package com.simplicite.optionmenu;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.$;

public class Cache {

    //not functionnal TODO
    public static void shortcut(RemoteWebDriver driver, String type) {
        Actions keyAction = new Actions(driver);
        keyAction.keyDown(Keys.ALT).sendKeys("c").sendKeys().keyUp(Keys.ALT).keyUp(Keys.SHIFT).perform();
    }

    public static void click(char type) {
        if (type == 'u')
            $("*[src$=\"cc1.png\"]").click();

        else if (type == 's')
            $("*[src$=\"cc2.png\"]").click();

        else if (type == 'c') {
            $("*[src$=\"cc3.png\"]").click();
            if ($(".btn-OK[data-action=\"OK\"]").exists())
                $(".btn-OK[data-action=\"OK\"]").click();
        }
        //$("#auth-main").should(Condition.exist, Duration.ofSeconds(60));
    }
}
