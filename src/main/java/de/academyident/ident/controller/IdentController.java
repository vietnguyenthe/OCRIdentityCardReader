package de.academyident.ident.controller;

import de.academyident.ident.model.BundesDatenbank;
import de.academyident.ident.model.Personendokument;
import de.academyident.ident.repository.BundesDatenbankRepo;
import de.academyident.ident.repository.PersonendokumentRepo;
import de.academyident.ident.util.Validierung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("neueDokumentDaten")
public class IdentController {

    @Autowired
    private PersonendokumentRepo personendokumentRepo;

    @Autowired
    private BundesDatenbankRepo bundesDatenbankRepo;


    @GetMapping (value = "/")
    public String eingabeFormularAnzeigen(Model model) {

        model.addAttribute("neueDokumentDaten", new Personendokument());

        return "startseite";
    }

    @PostMapping(value = "/datenUebergabe")
    public String datenAnnahmeUndValidierung(Model model,
                                             @ModelAttribute("neueDokumentDaten") Personendokument dokument) {

        if (Validierung.pruefeAusweisEchtheit(dokument)) {
            dokument.setDokumentIstEcht(true);
            return "pruefung";
        } else {
            return "startseite";
        }
    }

     //Methode führt zu html Bearbeitung
    @GetMapping(value="/datenBearbeiten")
    public String bearbeiteDaten(Model model){
        return "bearbeitung";
    }

    @GetMapping(value = "/ergebnisAnzeigen")
    public String ergebnisAnzeigen(Model model,
                                   @ModelAttribute("neueDokumentDaten") Personendokument dokument) {
        List<BundesDatenbank> bundesDatenbank = new ArrayList<>();
        bundesDatenbank = bundesDatenbankRepo.findAll();
        if(Validierung.pruefeObRealePerson(dokument, bundesDatenbank)){
            personendokumentRepo.save(dokument);
            return "ergebnis";
        }else{
            //hier muss noch auf eine Alternativseite verlinkt werden, falls der Datenbankabgleich negativ ist
            return "startseite  ";
        }
    }
    /*
     * TODO: BONUS: Bei false zurück auf die erste View mit Fehlermeldung
     * */

    /*
     * TODO: BONUS: Bei false Fehlermeldung anzeigen.
     *  */




}
