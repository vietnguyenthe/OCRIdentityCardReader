package de.academyident.ident.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Personendokument {

    @Id
    @GeneratedValue
    private int id;

    // Anschrift
    private String vorname;
    private String nachname;
    private String strasse;
    private int hausNr;
    private int plz;
    private String stadt;

    // Dokumentmerkmale
    private String echtheitsMerkmal;
    private String staatsangehoerigkeit;
    private String ausweisId;
    private String geburtsDatum;
    private String geburtsOrt;

    // Validierungsinformationen
    private boolean dokumentIstEcht = false;
    private boolean bestaetigtRealePerson = false; // Zweite Prüfung mit "externer" DB


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

    public String getEchtheitsMerkmal() {
        return echtheitsMerkmal;
    }

    public void setEchtheitsMerkmal(String echtheitsMerkmal) {
        this.echtheitsMerkmal = echtheitsMerkmal;
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

    public boolean isDokumentIstEcht() {
        return dokumentIstEcht;
    }

    public void setDokumentIstEcht(boolean dokumentIstEcht) {
        this.dokumentIstEcht = dokumentIstEcht;
    }

    public boolean isBestaetigtRealePerson() {
        return bestaetigtRealePerson;
    }

    public void setBestaetigtRealePerson(boolean bestaetigtRealePerson) {
        this.bestaetigtRealePerson = bestaetigtRealePerson;
    }
}
