package com.simplicite.menu.usersandrights;

import com.simplicite.utils.Component;

import static com.simplicite.menu.MainMenuProperties.domain;
import static com.simplicite.menu.MainMenuProperties.work;

public class Grant {


    public static void click() {
        Component.clickMenu(domain,"Grant");
    }
    public static void createObject(String grp_name, String mdl_name, String fct_name) {
        Component.sendFormControl(work.find("#field_grt_group_id__grp_name"), grp_name);
        Component.sendFormControl(work.find("#field_row_module_id__mdl_name"), mdl_name);
        Component.sendFormControl(work.find("#field_grt_function_id__fct_name"), fct_name);
    }

    public void find() {

    }
}
