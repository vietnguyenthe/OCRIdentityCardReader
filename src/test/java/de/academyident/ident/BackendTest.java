package de.academyident.ident;

import de.academyident.ident.model.BundesDatenbank;
import de.academyident.ident.model.Personendokument;
import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.util.*;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackendTest {

    Personendokument personendokument;
    List<BundesDatenbank> bundesDatenbankList = new ArrayList<>();

    @BeforeClass
    public void bundesDatenbankListeErstellen() {
        BundesDatenbank florianWeiss = new BundesDatenbank("Florian", "Weiss", "blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank vietNg = new BundesDatenbank("Viet", "Ng", "blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank oliverHock = new BundesDatenbank("Oliver", "Hock", "blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank peterMaier = new BundesDatenbank("Peter", "Maier", "blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank peterWeiss = new BundesDatenbank("Peter", "Weiss", "blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        bundesDatenbankList.add(florianWeiss);
        bundesDatenbankList.add(vietNg);
        bundesDatenbankList.add(oliverHock);
        bundesDatenbankList.add(peterMaier);
        bundesDatenbankList.add(peterWeiss);

        personendokument = new Personendokument();

    }



  /*
    Test der Klasse Validierung
     */

    @Test
    public void pruefeEchtheitFalsch() {
        personendokument.setEchtheitsMerkmal("Führerschein");
        Assert.assertFalse(Validierung.pruefeAusweisEchtheit(personendokument));
    }

    @Test
    public void pruefeEchtheitRichtig() {
        personendokument.setEchtheitsMerkmal("IDD");
        Assert.assertTrue(Validierung.pruefeAusweisEchtheit(personendokument));
    }

    @Test
    public void pruefeDatenbankRichtig() {
        personendokument.setVorname("Florian");
        personendokument.setNachname("Weiss");
        Assert.assertTrue(Validierung.pruefeObRealePerson(personendokument, bundesDatenbankList));
    }


    @Test
    public void pruefeDatenbankFalsch() {
        personendokument.setVorname("Florian");
        personendokument.setNachname("Müller");
        Assert.assertFalse(Validierung.pruefeObRealePerson(personendokument, bundesDatenbankList));
    }


/*
Tests der Klasse TesseractIdent
 */

    @Test
    public void leseTextAusRichtigerDateipfad() {
        File file = new File("src\\main\\resources\\tesseract\\Muster_des_Personalausweises_RS.jpg");
        Assert.assertNotNull(TesseractIdent.leseTextaus(file));
    }

    @Test
    public void leseTextAusFehlendenOderFehlerhaftenDateipfad() {
        File file = new File("src\\main\\resources\\tesseract\\Nicht_Vorhanden.jpg");
        Assert.assertNull(TesseractIdent.leseTextaus(file));
    }

/*
Test der Klasse SubbildErsteller
 */

    @Test
    public void erhalteRueckseiteOK() {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        Assert.assertNotNull(subbildErsteller.
                erhalteRueckseite("src\\main\\resources\\tesseract\\Muster_des_Personalausweises_RS.jpg"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void erhalteRueckseiteException() {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        Assert.assertNotNull
                (subbildErsteller.
                        erhalteRueckseite("src\\main\\resources\\tesseract\\Nicht_vorhanden.jpg"));

    }

    @Test
    public void erhalteVorderseiteOK() {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        Assert.assertNotNull(subbildErsteller.
                erhalteVorderseite("src\\main\\resources\\tesseract\\Muster_des_Personalausweises_VS.jpg"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void erhalteVorderseiteException() {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        Assert.assertNotNull
                (subbildErsteller.
                        erhalteVorderseite("Nicht vorhanden"));
    }

    @Test
    public void testeErstellungSubbilderOK() {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        subbildErsteller.erstelleAdresse("src\\main\\resources\\tesseract\\Muster_des_Personalausweises_RS.jpg");
        subbildErsteller.erstelleGeburtsort("src\\main\\resources\\tesseract\\Muster_des_Personalausweises_VS.jpg");
        subbildErsteller.erstelleMaschinenlesbareZone("src\\main\\resources\\tesseract\\Muster_des_Personalausweises_RS.jpg");
        File adresse = new File("src\\main\\resources\\tesseract\\adresse.jpg");
        Assert.assertNotNull(adresse);
        File geburtsort = new File("src\\main\\resources\\tesseract\\geburtsort.jpg");
        Assert.assertNotNull(geburtsort);
        File mlz = new File("src\\main\\resources\\tesseract\\maschinenLesbareZone.jpg");
        Assert.assertNotNull(mlz);
        List<String> dateien = new ArrayList<>(Arrays.asList("src\\main\\resources\\tesseract\\adresse.jpg",
                "src\\main\\resources\\tesseract\\geburtsort.jpg",
                "src\\main\\resources\\tesseract\\maschinenLesbareZone.jpg"));
        LokaleBilddateien.loeschen(dateien);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testeErstellungSubbilderException() {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        subbildErsteller.erstelleAdresse("nicht vorhanden");
        subbildErsteller.erstelleGeburtsort("nicht vorhanden");
        subbildErsteller.erstelleMaschinenlesbareZone("nicht vorhanden");
        File adresse = new File("src\\main\\resources\\tesseract\\adresse.jpg");
        Assert.assertNotNull(adresse);
        File geburtsort = new File("src\\main\\resources\\tesseract\\geburtsort.jpg");
        Assert.assertNotNull(geburtsort);
        File mlz = new File("src\\main\\resources\\tesseract\\maschinenLesbareZone.jpg");
        Assert.assertNotNull(mlz);
    }


    /*
    Test der Klasse SaveFile
     */

    @Test(expectedExceptions = NullPointerException.class)
    public void testSaveFrontOnDiskLeer() {
        TesseractFile tesseractFile = new TesseractFile();
        String test = "test";
        SaveFile.saveFrontOnDisk(tesseractFile, test);

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSaveBackOnDiskLeer() {
        TesseractFile tesseractFile = new TesseractFile();
        String test = "test";
        SaveFile.saveBackOnDisk(tesseractFile, test);

    }

    /*
    Test der Klasse LokaleBilddateien
     */
    @Test
    public void testLoeschenListeLeer(){
        List<String> paths =new ArrayList();
        String pfad = "nicht_vorhanden";
        paths.add(pfad);
        LokaleBilddateien.loeschen(paths);
    }

     /*
    Test der Klasse DeutscherAusweisOCR
     */

}

