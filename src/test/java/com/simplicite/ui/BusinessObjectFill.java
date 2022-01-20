package com.simplicite.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.create;
import static com.simplicite.menu.MainMenuProperties.save;

public class BusinessObjectFill {

    public static void createSupplier(String site, String logo, String telephone, String nom, String code){
        create();
        $("#field_trnSupSite").setValue(site);
        $("#field_trnSupTelephone").setValue(telephone);
        $("#field_trnSupNom").setValue(nom);
        $("#field_trnSupCode").setValue(code);
        save();
    }

    public static void createProduct(String description, String nom, String stock, String prix, String reference, String trnsuppliercode){
        create();
        $("#field_trnPrdDescription").setValue(description);
        $("#field_trnPrdNom").setValue(nom);
        $("#field_trnPrdStock").setValue(stock);
        $("#field_trnPrdPrix").setValue(prix);
        $("#field_trnPrdReference").setValue(reference);
        $("#field_trnPrdSupId__trnSupCode").click();
        find("trnSupCode", trnsuppliercode);
        save();
    }

    public static void createClient(String adress, String telephone, String mail, String prenom, String nom){
        create();
        $("#field_trnCliAdresse").setValue(adress);
        $("#field_trnCliTelephone").setValue(telephone);
        $("#field_trnCliMail").setValue(mail);
        $("#field_trnCliPrenom").setValue(prenom);
        $("#field_trnCliNom").setValue(nom);
        save();
    }

    public static void createOrder(String trnprdref, String trncliNom, String date, String quantite, String numero){
        create();
        $("#field_trnOrdPrdId__trnPrdReference").click();
        find("trnPrdReference", trnprdref);
        $("#field_trnOrdCliId__trnCliNom").click();
        find("trnCliNom", trncliNom);

        $("#field_trnOrdDate").setValue(date);
        $("#field_trnOrdQuantite").setValue(quantite);
        $("#field_trnOrdNumero").setValue(numero);
        save();
    }

    public static void find(String id, String idtext) {
        $("#" + id).setValue(idtext).pressEnter();
        Selenide.sleep(1000);
        $("[data-field=\"" + id+ "\"]").shouldHave(Condition.text(idtext)).click();
    }



    public static void performAction(String name) {
        $("[data-action=\"" + name + "\"]").click();
        SelenideElement element = $("#dlgmodal").find("[data-action=\"YES\"]");
        actions().pause(Duration.ofSeconds(1)).moveToElement(element).click().perform();
    }
}
