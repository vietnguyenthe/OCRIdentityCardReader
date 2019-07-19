package de.academyident.ident.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class BundesDatenbank {

    @Id
    @GeneratedValue
    private int id;

    public BundesDatenbank() {
    }

    public BundesDatenbank(String vorname, String nachname, String strasse, int hausNr, int plz, String stadt,
                           String staatsangehoerigkeit, String ausweisId, String geburtsDatum, String geburtsOrt) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.hausNr = hausNr;
        this.plz = plz;
        this.stadt = stadt;
        this.staatsangehoerigkeit = staatsangehoerigkeit;
        this.ausweisId = ausweisId;
        this.geburtsDatum = geburtsDatum;
        this.geburtsOrt = geburtsOrt;
    }

    // Anschrift
    private String vorname;
    private String nachname;
    private String strasse;
    private int hausNr;
    private int plz;
    private String stadt;

    // Dokumentmerkmale

    private String staatsangehoerigkeit;
    private String ausweisId;
    private String geburtsDatum;
    private String geburtsOrt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausNr() {
        return hausNr;
    }

    public void setHausNr(int hausNr) {
        this.hausNr = hausNr;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getStaatsangehoerigkeit() {
        return staatsangehoerigkeit;
    }

    public void setStaatsangehoerigkeit(String staatsangehoerigkeit) {
        this.staatsangehoerigkeit = staatsangehoerigkeit;
    }

    public String getAusweisId() {
        return ausweisId;
    }

    public void setAusweisId(String ausweisId) {
        this.ausweisId = ausweisId;
    }

    public String getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(String geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getGeburtsOrt() {
        return geburtsOrt;
    }

    public void setGeburtsOrt(String geburtsOrt) {
        this.geburtsOrt = geburtsOrt;
    }

}
