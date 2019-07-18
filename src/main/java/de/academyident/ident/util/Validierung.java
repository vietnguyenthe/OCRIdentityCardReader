package de.academyident.ident.util;

import de.academyident.ident.model.BundesDatenbank;
import de.academyident.ident.model.Personendokument;

import java.util.List;

public class Validierung {


    public static boolean pruefeAusweisEchtheit(Personendokument dokument) {

        return dokument.getEchtheitsMerkmal().equals("Personalausweis");
    }

        //TODO: Datenbankanbindung mit "Drittdatenbank" herstellen aka. Bundesamt für Bürger?
        //TODO: Datensatz abfragen (new Personendokument)
        //TODO: Übergebenes Dokument mit Datensatz abgleichen (möglichst viele Informationen)
        //TODO: Echtheitskritierien festlegen und return entsprechend anpassen/einbauen
    //umgesetzt wurde die Abfrage ob Vorname und Nachname aus Dokument mit einem Eintrag in BundesDBübereinstimmen
    public static boolean pruefeObRealePerson(Personendokument dokument, List<BundesDatenbank> bundesDB) {
        for (BundesDatenbank eintrag : bundesDB) {
            if (eintrag.getVorname().equals(dokument.getVorname())) {
                if (eintrag.getNachname().equals(dokument.getNachname())) {
                    return true;
                }
            }
        }
        return false;
    }

}
