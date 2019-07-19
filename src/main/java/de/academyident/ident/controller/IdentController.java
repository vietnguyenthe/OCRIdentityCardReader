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

@Controller
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
    @PostMapping(value="/datenBearbeiten")
    public String bearbeiteDaten(Model model){
        return "bearbeitung";
    }

    @PostMapping(value = "/ergebnisAnzeigen")
    public String ergebnisAnzeigen(Model model,
                                   @ModelAttribute("neueDokumentDaten") Personendokument dokument) {
        List<BundesDatenbank> bundesDatenbank = new ArrayList<>();
        bundesDatenbank = bundesDatenbankRepo.findAll();
        if(Validierung.pruefeObRealePerson(dokument, bundesDatenbank)){
            personendokumentRepo.save(dokument);
            return "ergebnis";
        }else{
            //hier muss noch auf eine Alternativseite verlinkt werden, falls der Datenbankabgleich negativ ist
            return "startseite";
        }
    }
    /*
     * TODO: "/Echtheitsmerkmalpruefung"
     * TODO: Im Controller das von der View befüllte Model abrufen
     * TODO: Echtheitsmerkmal mit Hilfe der Validierungsklasse prüfen
     * TODO: Bei true den Datensatz anpassen (istEcht = true)
     * TODO: Zweite View aufrufen und angepasstes Model übergeben
     * TODO: BONUS: Bei false zurück auf die erste View mit Fehlermeldung
     * */

    /*
     * TODO: User bestätigt korrekte Eingabe in der View
     * TODO: Controller erhält Model zurück und fragt Validierung obEchtePerson ab
     * TODO: Bei true wird die nächste View aufgerufen und das Model übergeben
     * TODO: BONUS: Bei false Fehlermeldung anzeigen.
     *  */




    //////////                                                   \\\\\\\\\\
    ////////// Methoden des Webblog Controllers als Hilfestellen \\\\\\\\\\
    //////////                                                   \\\\\\\\\\

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String beitraegeAnzeigen(Model model) {

        List<Beitrag> alleBeitraege = beitragRepository.findAll();

        model.addAttribute("beitragList", alleBeitraege);

        return "beitraege-anzeigen";
    }

    @GetMapping(value = "/beitragErstellen")
    public String beitragErstellen(Model model) {

        model.addAttribute("neuerBeitrag", new Beitrag());

        return "beitrag-erstellen";
    }

    @PostMapping(value = "/beitragHinzufuegen")
    public String beitragHinzufuegen(
            Model model, @ModelAttribute("neuerBeitrag") Beitrag beitrag
    ) {

        //beitraege.add(beitrag);
        beitragRepository.save(beitrag);

        return "redirect:/";
    }

    @GetMapping(value = "/kommentarAnzeigen")
    public String kommentareAnzeigen(Model model, @RequestParam("id") Integer id) {

        Optional<Beitrag> optionalBeitrag = beitragRepository.findById(id);
        Beitrag gefundenenBeitrag = optionalBeitrag.get();

        model.addAttribute("selektierterBeitrag", gefundenenBeitrag);

        model.addAttribute("neuerKommentar", new Kommentar());

        return "kommentare";
    }

    private Beitrag findeBeitrag(@RequestParam("id") String id) {
        Beitrag gefundenenBeitrag = null;
        for (Beitrag beitrag : beitraege) {
            if (id.equals(beitrag.getId())) {
                gefundenenBeitrag = beitrag;
                break;
            }
        }
        return gefundenenBeitrag;
    }

    *//*
     * Finde einen Beitrag anhand seiner ID.
     *//*

    @PostMapping(value = "/kommentarHinzufuegen")
    public String kommentarHinzufuegen(
            Model model,
            @ModelAttribute Kommentar neuerKommentar,
            @RequestParam("id") Integer id
    ) {

        Beitrag beitrag = beitragRepository.findById(id).get();
        neuerKommentar.setBeitrag(beitrag);

        kommentarRepository.save(neuerKommentar);

        return "redirect:/";
    }*/

}
