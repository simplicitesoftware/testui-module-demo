package io.simplicite.Simplinium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Form {

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

    /** Click the button close
     *
     */
    public static void close() {
        $("button[data-action=\"close\"]").click();
    }

    /** Click the button save
     *
     */
    public static void save() {
        $("button[data-action=\"save\"]").click();
        $(".alert").should(Condition.and("saverule", Condition.exist,Condition.text("Save OK.")));
    }

    /** Click the button save&close
     *
     */
    public static void saveAndClose() {
        $("button[data-action=\"saveclose\"]").click();
    }

    public static void clickOnButtonEndDlgmodal(String component) {
        SelenideElement dlgmodal = $("#dlgmodal").shouldHave(Condition.cssClass("show"));
        Selenide.sleep(1000);
        dlgmodal.find(component).click();
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
