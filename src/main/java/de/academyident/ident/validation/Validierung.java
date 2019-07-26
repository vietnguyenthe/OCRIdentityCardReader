package de.academyident.ident.validation;

import de.academyident.ident.model.BundesDatenbank;
import de.academyident.ident.model.Personendokument;

import java.util.List;

public class Validierung {


    public static boolean pruefeAusweisEchtheit(Personendokument dokument) {

        return dokument.getEchtheitsMerkmal().equals("IDD");
    }

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
