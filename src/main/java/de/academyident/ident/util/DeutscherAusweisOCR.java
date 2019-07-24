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

    String zeilePLZstadt = "00000 Unleserlich"; // PLZ Stadt
    String zeileStrasseHausNr = "Unleserlich 00"; // Strasse Hausnummer

    String plz = "00000";
    String stadt = "Unleserlich";
    String strasse = "Unleserlich";
    String hausnummer = "00";

    public DeutscherAusweisOCR(String maschinellerText, String adresseText, String geburtsortText) {

        // Maschineller Bereich, der für OCR optimiert ist (Schriftart, Abstände, Größe)
        maschBereichLinienTrennen(maschinellerText);
        processLineOne(firstLine);
        processLineTwo(secondLine);
        processLineThree(thirdLine);

        // Bildausschnitt anderer Bereiche
        // In diesen Bereichen wird eine höhere Fehlerrate erwartet, da diese nicht optimiert sind
        verarbeiteAdresse(adresseText);
        verarbeiteGeburtsort(geburtsortText);
    }

    private void verarbeiteGeburtsort(String geburtsortText) {
        List<String> zeilen = new ArrayList<String>(Arrays.asList(geburtsortText.split("\n")));

        String zeileZwei = zeilen.get(1).replaceAll("[^A-Z0-9<]", "")
                .trim(); // Geburtsort

        zeileZwei = toLowercaseButFirstChar(zeileZwei);

        resultMap.put("geburtsort", zeileZwei);
    }

    private void verarbeiteAdresse(String adresseText) {
        List<String> zeilen = new ArrayList<String>(Arrays.asList(adresseText.split("\n")));

        // Entfernt überflüssige Zeilen, die zu kurz sind, um den benötigen Inhalt zu haben
        List<String> zeilenLoeschen = new ArrayList<>();
        for (String zeile: zeilen) {
            if (zeile.length() < 5) {
                zeilenLoeschen.add(zeile);
            }
        }
        zeilen.removeAll(zeilenLoeschen);

        // Nur bei drei Zeilen wir versucht die ausgelesenen Date zu verwenden, da dies das optimale Ergebnis sein sollte
        // Mehr oder weniger Zeilen deuten auf einen Verlust oder schwer auslesbare Daten hin
        if (zeilen.size() == 3) {
            zeilePLZstadt = zeilen.get(1);
            zeileStrasseHausNr = zeilen.get(2);
        }

        // Die Zeilen werden um alle Sonderzeichen bereinigt, außer Bindestriche wegen Straßennamen
        zeilePLZstadt = zeilePLZstadt.replaceAll("[^a-zA-Z0-9\\-\" \"]", "")
                             .trim();
        zeileStrasseHausNr = zeileStrasseHausNr.replaceAll("[^a-zA-Z0-9\\-\" \"]", "")
                             .trim();

        List<String> plzStadtListe = new ArrayList<String>(Arrays.asList(zeilePLZstadt.split(" ")));
        List<String> strasseHausNrListe = new ArrayList<String>(Arrays.asList(zeileStrasseHausNr.split(" ")));

        // Es wird nach der PLZ gesucht, Kriterium ist hierbei nur die Länge
        // Die Hausnummer wir direkt als folgendes Element erwartet und ausgewertet
        for (int i = 0; i < plzStadtListe.size();i++) {
            if (plzStadtListe.get(i).length() == 5) {
                plz = plzStadtListe.get(i);
                stadt = plzStadtListe.get(i+1);
                break;
            }
        }

        // Es wird davon ausgegangen, dass Straßennamen eine Mindestlänge von 5 haben und die Hausnummer direkt darauf folgt
        for (int i = 0; i < strasseHausNrListe.size();i++) {
            if (strasseHausNrListe.get(i).length() > 5) {
                strasse = strasseHausNrListe.get(i);
                hausnummer = strasseHausNrListe.get(i+1);
                break;
            }
        }

        stadt = toLowercaseButFirstChar(stadt);
        strasse = toLowercaseButFirstChar(strasse);

        resultMap.put("plz", plz);
        resultMap.put("stadt", stadt);
        resultMap.put("strasse", strasse);
        resultMap.put("hausnummer", hausnummer);
    }

    private void maschBereichLinienTrennen(String OCRresult) {
        List<String> resultAsLines = new ArrayList<>(Arrays.asList(OCRresult.split("\n")));

        firstLine = resultAsLines.get(0); // IDD<<1T220001293 - IDD Länderkürzel, Ausweisnummer (immer char am Start)
        secondLine = resultAsLines.get(1); // Geburtsdatum (YYMMDD), Gültigkeit (YYMMDD), Prüfziffer, Herkunftsland
        thirdLine = resultAsLines.get(2); // Nachname, Vorname
    }

    private void processLineOne(String line) {
        line = line.replaceAll("[^A-Z0-9<]", "").trim();
        List<String> subStrings = new ArrayList<String>(Arrays.asList(line.split("<")));
        bereinigeSubstringListe(subStrings);

        if (subStrings.get(0).equals("IDD")){
            resultMap.put("echtheitsmerkmal", subStrings.get(0));
        }

        String ausweisnummer = subStrings.get(1);
        while (!Character.isLetter(ausweisnummer.charAt(0))) { // Ausweisnr startet immer mit einem Buchstaben
            ausweisnummer = ausweisnummer.substring(1);        // Fehlerhafte Zeichen vor der ID entfernen
        }
        ausweisnummer = ausweisnummer.substring(0,9); // entfernt die Prüfziffer und ggf. zusätzlich char am Ende

        resultMap.put("ausweisnummer", ausweisnummer);

        // TODO: Eventuell eine Prüfung bzgl. Länge der Nummer einbauen.
    }

    private void processLineTwo(String line) {
        line = line.replaceAll("[^A-Z0-9<]", "").trim();
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
        line = line.replaceAll("[^A-Z0-9<]", "").trim();

        List<String> subStrings = new ArrayList<String>(Arrays.asList(line.split("<")));
        bereinigeSubstringListe(subStrings);

        String nachname = subStrings.get(0);
        nachname = toLowercaseButFirstChar(nachname);
        resultMap.put("nachname", nachname);

        StringBuffer vornameUndBeiname = new StringBuffer();
        for (int i = 1; i < subStrings.size(); i++) {
            String name = subStrings.get(i);
            name = toLowercaseButFirstChar(name);
            vornameUndBeiname.append(name);
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

    private String toLowercaseButFirstChar(String string) {
        string = string.charAt(0) + string.substring(1).toLowerCase();
        return string;
    }

    public HashMap<String, String> getResultMap() {
        return resultMap;
    }
}
