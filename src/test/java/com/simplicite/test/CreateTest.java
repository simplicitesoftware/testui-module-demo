package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.BusinessObject;
import com.simplicite.menu.administration.Module;
import com.simplicite.menu.templateeditor.TemplateEditorBO;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyTestWatcher.class)
@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.DisplayName.class)
public class CreateTest {

    @BeforeAll
    public static void setUpAll() {
        try {
            var in = new FileReader("src/test/resources/config.properties");
            Datastore.PROPERTIES.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = Datastore.PROPERTIES.getProperty("browsersize");
        //Configuration.startMaximized = true;
        Configuration.browser = Datastore.PROPERTIES.getProperty("browser");
        Configuration.headless = Datastore.PROPERTIES.getProperty("headless").equals("true");
        Configuration.savePageSource = false;
        Configuration.pageLoadTimeout = Integer.parseInt(Datastore.PROPERTIES.getProperty("pageLoadTimeout"));
        Configuration.timeout = Integer.parseInt(Datastore.PROPERTIES.getProperty("timeout"));
        Configuration.pollingInterval = Integer.parseInt(Datastore.PROPERTIES.getProperty("pollingInterval"));
        Datastore.initUser();

    }

    @AfterAll
    public static void setDownAll() {
        try {
            var out = new FileWriter("src/test/resources/config.properties");
            Datastore.PROPERTIES.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        Selenide.open(Datastore.PROPERTIES.getProperty("url"));

        if (Authentication.isAuthentificationPage()) {
            Authentication.connect(Datastore.USERNAME, Datastore.NEW_PASSWORD);
        }
    }

    @AfterEach
    public void close() {
        DropDownMenu.click(4);
        Cache.click('c');
    }

    @Test
    @Order(1)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createModule() {
        Module.click();
        Module.createModuleAssistant(Datastore.MODULE, "alc", Datastore.GROUP, Datastore.DOMAIN, "img/color/console");
        assertTrue(Module.isSuccess(Datastore.MODULE));
    }


    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelSupplier() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(Datastore.SUPPLIER, "alc_supplier", Datastore.MODULE, "sup", Datastore.DOMAIN);

        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(Datastore.SUPPLIERAREA1, "code", "alcSupCode", 3, true, true);
        TemplateEditorBO.addField(Datastore.SUPPLIERAREA1, "nom", "alcSupNom", 3, false, false);
        TemplateEditorBO.addField(Datastore.SUPPLIERAREA1, "téléphone", "alcSupTelephone", 22, false, false);
        TemplateEditorBO.addField(Datastore.SUPPLIERAREA1, "logo", "alcSupLogo", 20, false, false);
        TemplateEditorBO.addField(Datastore.SUPPLIERAREA1, "site", "alcSupSite", 10, false, false);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelProduct() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(Datastore.PRODUCT, "alc_product", Datastore.MODULE, "prd", Datastore.DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(Datastore.PRODUCTAREA1, "référence", "alcPrdReference", 3, true, true);
        TemplateEditorBO.addField(Datastore.PRODUCTAREA1, "prix", "alcPrdPrix", 2, true, false);
        TemplateEditorBO.addField(Datastore.PRODUCTAREA1, "stock", "alcPrdStock", 1, true, false);
        TemplateEditorBO.addField(Datastore.PRODUCTAREA1, "nom", "alcPrdNom", 3, false, false);
        TemplateEditorBO.addField(Datastore.PRODUCTAREA1, "description", "alcPrdDescription", 13, false, false);
        TemplateEditorBO.addField(Datastore.PRODUCTAREA1, "photo", "alcPrdPhoto", 20, false, false);
        TemplateEditorBO.saveEditor();
    }
/*
    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelClient() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(CLIENT, "alc_client", MODULE, "cli", DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(CLIENTAREA1, "nom", "alcCliNom", 3, true, true);
        TemplateEditorBO.addField(CLIENTAREA1, "prénom", "alcCliPrenom", 3, true, true);
        TemplateEditorBO.addField(CLIENTAREA1, "mail", "alcCliMail", 12, false, false);
        TemplateEditorBO.addField(CLIENTAREA1, "téléphone", "alcCliTelephone", 22, false, false);
        TemplateEditorBO.addField(CLIENTAREA1, "adresse", "alcCliAdresse", 25, false, false);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelOrder() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(ORDER, "alc_order", MODULE, "ord", DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(ORDERAREA1, "numéro", "alcOrdNumero", 3, true, true);
        TemplateEditorBO.addField(ORDERAREA1, "quantité", "alcOrdQuantite", 1, true, false);
        TemplateEditorBO.addField(ORDERAREA1, "date", "alcOrdDate", 4, false, false);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(6)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createLink() {
        Link.addLink(ORDER, CLIENT);
        Link.addLink(ORDER, PRODUCT);
        Link.addLink(PRODUCT, SUPPLIER, "AlcSupNom");
    }

    @Test
    @Order(7)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createArea() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addRow();
        TemplateEditorBO.addArea();
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "alcCliNom");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "alcCliPrenom");
        TemplateEditorBO.addArea();
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "alcPrdReference");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "alcPrdNom");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "alcPrdStock");
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(8)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createAction() {
        Action.click();
        Action.createAction(INCREASESTOCK, MODULE, "javascript:alert(\"To be implemented...\")");
        Action.addFunction(PRODUCT, "TRN_PRD_INCREASE_STOCK_A");
        Function.associateGroup(GROUP);
    }

    @Test
    @Order(9)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void changeTemplateHtml() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.setEditorTemplate(ORDERTEMPLATEHTML);
        TemplateEditorBO.closeEditor();
    }

    @Test
    @Order(10)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createDiagram() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addField(ORDERAREA1, "State", FIELDORDERSTATE, 7, true, false);
        TemplateEditorBO.saveEditor();

        TemplateEditorBO.modifyField(FIELDORDERSTATE);
        TemplateEditorBO.editEnum(LISTORDERSTATE);

        TemplateEditorBO.saveEditor();
        TemplateEditorBO.closeEditor();

        TemplateEditorBO.addStateModel(LISTACCESSORDERSTATE, GROUP, LISTTRADORDERSTATE);
    }

    @Test
    @Order(11)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createHistoric() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.accessToOption();
        MainMenuProperties.save();

        SystemProperties.click();
        SystemProperties.find("LOG_ACTIVITY");
        assertTrue(SystemProperties.verifyValue());

        Authentication.changeState("Home", "VIEW_ADMIN");
        ModuleActive.click();
        ModuleActive.showAll();
        Function.click();
        Function.find(REDO);
        Function.setModuleName(MODULE);
        MainMenuProperties.save();

        Function.associateGroup(GROUP);
        MainMenuProperties.save();

        ModuleActive.click();
        ModuleActive.select("MODULE");
        Authentication.changeState("ViewDesign", "design");
    }

    @Test
    @Order(12)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createPivotTable() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "alcPrdSupId");
        Selenide.sleep(1000);
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "alcPrdSupId__alcSupNom");
        TemplateEditorBO.saveEditor();

        PivotTable.click();
        PivotTable.createPivotTable(PIVOTTABLE, ORDER, MODULE);
        PivotTable.createPivotAxis(ORDER, "alcSupNom", 'C', 10);
        PivotTable.createPivotAxis(ORDER, "alcPrdNom", 'C', 20);
        PivotTable.createPivotAxis(ORDER, FIELDORDERSTATE, 'L', 30);
    }

    @Test
    @Order(13)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createView() {
        Views.click();
        Views.createView(HOME, MODULE, 10);
        TemplateEditorView.navigateToEditor();
        TemplateEditorView.modifyArea("1:=");
        TemplateEditorView.setCrossTable(PIVOTTABLE, ORDER);

        TemplateEditorView.addField();
        TemplateEditorView.setProcessSearch(HOME, ORDER);
        TemplateEditorView.modifyArea("2");
        TemplateEditorView.setProcessSearchFilter(FIELDORDERSTATE, "PROCESSING");

        TemplateEditorView.saveEditor();

        Domain.click();
        Domain.find(DOMAIN);
        Domain.setHomePage(HOME);
    }

    @Test
    @Order(14)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createTheme() {
        Theme.click();
        Theme.createTheme(THEME);

        Views.click();
        Views.find(HOME);
        Views.setTheme(THEME);

        FieldStyle.click();
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, PROCESSING, "orangbg");
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, CANCELED, "greybg");
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, VALIDATED, "greenbg");
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, SENT, "bluebg");

        ListOfValue.click();
        ListOfValue.find(FIELDORDERSTATE);
        ListOfValue.modifyListCodeIcon(PROCESSING, "icon/color/btn_orange");
        ListOfValue.modifyListCodeIcon(CANCELED, "icon/color/btn_grey");
        ListOfValue.modifyListCodeIcon(VALIDATED, "icon/color/btn_green");
        ListOfValue.modifyListCodeIcon(SENT, "icon/color/btn_blue");
    }*/
}

