package de.academyident.ident.util;

import de.academyident.ident.model.Personendokument;

public class Validierung {


    public static boolean pruefeAusweisEchtheit(Personendokument dokument) {

        return dokument.getEchtheitsMerkmal().equals("Personalausweis");
    }

    public static boolean pruefeObRealePerson(Personendokument dokument) {

        //TODO: Datenbankanbindung mit "Drittdatenbank" herstellen aka. Bundesamt für Bürger?
        //TODO: Datensatz abfragen (new Personendokument)
        //TODO: Übergebenes Dokument mit Datensatz abgleichen (möglichst viele Informationen)
        //TODO: Echtheitskritierien festlegen und return entsprechend anpassen/einbauen

        return true;
    }


}
