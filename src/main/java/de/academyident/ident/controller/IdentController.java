package de.academyident.ident.controller;

import de.academyident.ident.repository.PersonendokumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IdentController {

    @Autowired
    private PersonendokumentRepo personendokumentRepo;

    @GetMapping (value = "/")
    public String eingabeFormularAnzeigen(Model model) {

        /*
         * TODO: "/"-Aufrufmethode erstellen
         * TODO: Personendokument als Model erstellen
         * TODO: Auf Startseite wechseln und Model übergeben (Modelattributes siehe Webblog
         */

        return "Irgeneine Seite";
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
