package de.academyident.ident.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DeutscherAusweisOCR {

    private HashMap<String, String> resultMap = new HashMap<>();

    private String firstLine;
    private String secondLine;
    private String thirdLine;


    public DeutscherAusweisOCR(String OCRergebnis) {

        nachLinienTrennen(OCRergebnis);
        processLineOne(firstLine);
        processLineTwo(secondLine);
        processLineThree(thirdLine);

    }

    private void nachLinienTrennen(String OCRresult) {
        List<String> resultAsLines = new ArrayList<String>(Arrays.asList(OCRresult.split("\n")));

        firstLine = resultAsLines.get(0); // IDD<<1T220001293 - IDD Länderkürzel, Ausweisnummer (immer char am Start)
        secondLine = resultAsLines.get(1); // Geburtsdatum (YYMMDD), Gültigkeit (YYMMDD), Prüfziffer, Herkunftsland
        thirdLine = resultAsLines.get(2); // Nachname, Vorname
    }

    private void processLineOne(String line) {
        List<String> subStrings = new ArrayList<String>(Arrays.asList(line.split("<")));
        bereinigeSubstringListe(subStrings);

        if (subStrings.get(0).equals("IDD")){
            resultMap.put("echtheitsmerkmal", subStrings.get(0));
        }

        String ausweisnummer = subStrings.get(1);
        while (!Character.isLetter(ausweisnummer.charAt(0))) { // Ausweisnr startet immer mit einem Buchstaben
            ausweisnummer = ausweisnummer.substring(1);        // Fehlerhafte Zeichen vor der ID entfernen
        }

        resultMap.put("ausweisnummer", ausweisnummer);

        // TODO: Eventuell eine Prüfung bzgl. Länge der Nummer einbauen.
    }

    private void processLineTwo(String line) {
        List<String> subStrings = new ArrayList<String>(Arrays.asList(line.split("<")));
        bereinigeSubstringListe(subStrings);

        // Geburtsdatum
        StringBuffer geburtsdatum = new StringBuffer();
        String geburtsdatumUnformatiert = subStrings.get(0);

        geburtsdatum.append(geburtsdatumUnformatiert.charAt(4));
        geburtsdatum.append(geburtsdatumUnformatiert.charAt(5));
        geburtsdatum.append(".");
        geburtsdatum.append(geburtsdatumUnformatiert.charAt(2));
        geburtsdatum.append(geburtsdatumUnformatiert.charAt(3));
        geburtsdatum.append(".");
        geburtsdatum.append(geburtsdatumUnformatiert.charAt(0));
        geburtsdatum.append(geburtsdatumUnformatiert.charAt(1));

        resultMap.put("geburtsdatum", geburtsdatum.toString());


        // Ablaufdatum
        StringBuffer ablaufdatum = new StringBuffer();
        String ablaufdatumUnformatiert = subStrings.get(1);

        ablaufdatum.append(ablaufdatumUnformatiert.charAt(4));
        ablaufdatum.append(ablaufdatumUnformatiert.charAt(5));
        ablaufdatum.append(".");
        ablaufdatum.append(ablaufdatumUnformatiert.charAt(2));
        ablaufdatum.append(ablaufdatumUnformatiert.charAt(3));
        ablaufdatum.append(".");
        ablaufdatum.append(ablaufdatumUnformatiert.charAt(0));
        ablaufdatum.append(ablaufdatumUnformatiert.charAt(1));

        resultMap.put("ablaufdatum", ablaufdatum.toString());

        resultMap.put("gesamtpruefziffer", subStrings.get(2));

        char herkunftslandID = ablaufdatumUnformatiert.charAt(7);
        if (herkunftslandID == 'D') {
            resultMap.put("herkunftsland", "Deutschland");
        }
    }

    private void processLineThree(String line) {
        List<String> subStrings = new ArrayList<String>(Arrays.asList(line.split("<")));
        bereinigeSubstringListe(subStrings);

        String nachname = subStrings.get(0);
        resultMap.put("nachname", nachname);

        StringBuffer vornameUndBeiname = new StringBuffer();
        for (int i = 1; i < subStrings.size(); i++) {
            vornameUndBeiname.append(subStrings.get(i));
        }
        resultMap.put("vorname", vornameUndBeiname.toString() );

    }

    private void bereinigeSubstringListe(List<String> subStrings) {

        List<String> zuEntfernen = new ArrayList<>();

        for (String element: subStrings) {
            if (element.equals("") || element.equals("<")) {
                zuEntfernen.add(element);
            }
        }

        subStrings.removeAll(zuEntfernen);
    }

    public HashMap<String, String> getResultMap() {
        return resultMap;
    }
}
