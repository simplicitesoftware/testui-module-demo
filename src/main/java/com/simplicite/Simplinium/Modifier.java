package com.simplicite.Simplinium;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class Modifier {

    /** Send a text to a component. Specific to the use of component which need to be click
     * before sending text then need to clear the text and have auto-completion in Simplicité.
     *
     * @param components The selenieelement wanted to send a text
     * @param text Text send to the selenideelement
     */
    public static void sendFormControl(SelenideElement components, String text) {
        Selenide.actions().moveToElement(components).click(components).pause(Duration.ofMillis(200))
                .sendKeys(Keys.ENTER).keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).sendKeys(text)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).perform();
    }

    /** Use to move slider in Simplicité
     *
     * @param id ID of the slider
     * @param value Value need to be reached by the slider
     */
    public static void setSliderValue(String id, int value) {
        SelenideElement slider = $("#" + id);
        for (int i = 1; i < value; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            Selenide.sleep(200);
        }
    }

    /** Switch the processing state to a certain state.
     *
     * @param state The state to switch
     */
    public static void switchProcessingState(String state) {
        Selenide.$("button[data-state=\"" + state + "\"]").click();
    }
}
