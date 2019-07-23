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

        StringBuffer datum = new StringBuffer();
        String datumUnformatiert = subStrings.get(0);

        datum.append(datumUnformatiert.charAt(4));
        datum.append(datumUnformatiert.charAt(5));
        datum.append(".");
        datum.append(datumUnformatiert.charAt(2));
        datum.append(datumUnformatiert.charAt(3));
        datum.append(".");
        datum.append(datumUnformatiert.charAt(0));
        datum.append(datumUnformatiert.charAt(1));

        resultMap.put("geburtsdatum", datum.toString());


    }

    private void processLineThree(String line) {
        List<String> subStrings = new ArrayList<String>(Arrays.asList(line.split("<")));
        bereinigeSubstringListe(subStrings);

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
