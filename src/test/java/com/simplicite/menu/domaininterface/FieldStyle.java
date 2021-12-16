package com.simplicite.menu.domaininterface;

import com.simplicite.utils.Component;

import static com.simplicite.menu.MainMenuProperties.*;

public class FieldStyle {

    public static void click() {
        Component.clickMenu(domaininterface,"FieldStyle");
    }

    public static void find(String name) {
        work.find("#sty_object").setValue(name);
        work.find("[data-field=\"sty_object\"]").click();
    }

    public static void createFieldStyle(String object, String field, String value, String color) {
        create();

        work.find("#field_sty_object").setValue(object);
        work.find("#field_sty_field").setValue(field);
        work.find("#field_sty_value").setValue(value);
        work.find("#field_sty_style").setValue(color);

        saveAndClose();
    }

}
