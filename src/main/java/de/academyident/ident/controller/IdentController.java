package de.academyident.ident.controller;

import de.academyident.ident.model.BundesDatenbank;
import de.academyident.ident.model.Personendokument;
import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.repository.BundesDatenbankRepo;
import de.academyident.ident.repository.PersonendokumentRepo;
import de.academyident.ident.filemanagement.LokaleBilddateien;
import de.academyident.ident.validation.Validierung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("neueDokumentDaten")
public class
IdentController {

    @Autowired
    private PersonendokumentRepo personendokumentRepo;

    @Autowired
    private BundesDatenbankRepo bundesDatenbankRepo;


    @GetMapping (value = "/")
    public String eingabeFormularAnzeigen(Model model) {

        model.addAttribute("neueDokumentDaten", new Personendokument());

        model.addAttribute("tessImage", new TesseractFile());

        return "startseite";
    }

    @PostMapping(value = "/datenUebergabe")
    public String datenAnnahmeUndValidierung(Model model,
                                             @ModelAttribute("neueDokumentDaten") Personendokument dokument) {

        if (Validierung.pruefeAusweisEchtheit(dokument)) {
            dokument.setDokumentIstEcht(true);
            return "pruefung";
        } else {
            return "nichtErfolgreich";
        }
    }

     //Methode führt zu html Bearbeitung
    @GetMapping(value="/datenBearbeiten")
    public String bearbeiteDaten(Model model,
                                 @ModelAttribute("neueDokumentDaten") Personendokument dokument){
        return "bearbeitung";
    }

    @PostMapping(value="/ueberarbeiteteDaten")
    public String ueberarbeiteteDatenPruefen(Model model,
                                             @ModelAttribute("neueDokumentDaten") Personendokument dokument)   {
        return "pruefung";
    }


    @GetMapping(value = "/ergebnisAnzeigen")
    public String ergebnisAnzeigen(Model model,
                                   @ModelAttribute("neueDokumentDaten") Personendokument dokument) {
        List<BundesDatenbank> bundesDatenbank = new ArrayList<>();
        bundesDatenbank = bundesDatenbankRepo.findAll();
        if(Validierung.pruefeObRealePerson(dokument, bundesDatenbank)){
            personendokumentRepo.save(dokument);

            List<String> dateien = new ArrayList<>(Arrays.asList("src\\main\\resources\\static\\img\\Perso_Back.jpg",
                                                                 "src\\main\\resources\\static\\img\\Perso_Front.jpg"));
            LokaleBilddateien.loeschen(dateien);

            return "ergebnis";
        }else{
            //hier muss noch auf eine Alternativseite verlinkt werden, falls der Datenbankabgleich negativ ist
            return "nichtErfolgreich";
        }
    }
}
