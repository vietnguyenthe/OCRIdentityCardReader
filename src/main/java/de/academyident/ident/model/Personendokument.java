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
    private LocalDate geburtsDatum;
    private String geburtsOrt;

    // Validierungsinformationen
    private boolean dokumentIstEcht = false;
    private boolean bestaetigtRealePerson = false; // Zweite Prüfung mit "externer" DB



}
