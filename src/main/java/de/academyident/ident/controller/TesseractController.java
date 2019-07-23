package de.academyident.ident.controller;

import de.academyident.ident.model.Personendokument;
import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.util.DeutscherAusweisOCR;
import de.academyident.ident.util.SaveFile;
import de.academyident.ident.util.SubbildErsteller;
import de.academyident.ident.util.TesseractIdent;
import org.bytedeco.opencv.presets.opencv_core;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.print.DocFlavor;
import java.io.File;
import java.util.HashMap;

@Controller
@SessionAttributes("neueDokumentDaten")
public class TesseractController {


    @PostMapping(value = "/fileUpload")
    public String bearbeiteDaten(Model model,
                                 @ModelAttribute("tessImage") TesseractFile tesseractFile,
                                 @ModelAttribute("neueDokumentDaten") Personendokument dokument){

        SaveFile.saveFrontOnDisk(tesseractFile, "\\Perso_Front.jpg");
        SaveFile.saveBackOnDisk(tesseractFile, "\\Perso_Back.jpg");

        SubbildErsteller subbildErsteller = new SubbildErsteller();

        subbildErsteller.erstelleMaschinenlesbareZone("src\\main\\resources\\tesseract\\Perso_Back.jpg");

        String maschinenlesbareZone = TesseractIdent.leseTextaus(
                new File("src\\main\\resources\\tesseract\\maschinenLesbareZone.jpg"));

        subbildErsteller.erstelleAdresse("src\\main\\resources\\tesseract\\Perso_Back.jpg");

        String adresse = TesseractIdent.leseTextaus(
                new File("src\\main\\resources\\tesseract\\adresse.jpg"));

        subbildErsteller.erstelleGeburtsort("src\\main\\resources\\tesseract\\Perso_Front.jpg");

        String geburtsort = TesseractIdent.leseTextaus(
                new File("src\\main\\resources\\tesseract\\geburtsort.jpg"));


        DeutscherAusweisOCR deutscherAusweisOCR = new DeutscherAusweisOCR(maschinenlesbareZone, adresse, geburtsort);

        HashMap<String, String> ergebnisMap = deutscherAusweisOCR.getResultMap();

        ocrModelMapping(dokument, ergebnisMap);

        return "pruefung";
    }

    private void ocrModelMapping(@ModelAttribute("neueDokumentDaten") Personendokument dokument, HashMap<String, String> ergebnisMap) {
        dokument.setNachname(ergebnisMap.get("nachname"));
        dokument.setVorname(ergebnisMap.get("vorname"));
        dokument.setStaatsangehoerigkeit(ergebnisMap.get("herkunftsland"));
        dokument.setGeburtsDatum(ergebnisMap.get("geburtsdatum"));
        dokument.setAusweisId(ergebnisMap.get("ausweisnummer"));
        dokument.setGeburtsOrt(ergebnisMap.get("geburtsort"));
        dokument.setHausNr(Integer.parseInt(ergebnisMap.get("hausnummer")));
        dokument.setPlz(Integer.parseInt(ergebnisMap.get("plz")));
        dokument.setStadt(ergebnisMap.get("stadt"));
        dokument.setStrasse(ergebnisMap.get("strasse"));
    }
}


// Diese Methonde kann die Brightness eines Bildes erhöhen. Der Float gibt dabei die % an (1.2 = 20%)
//        BufferedImage bufferedImage = null;
//        try {
//            bufferedImage = ImageIO.read(new File("src/main/resources/tesseract/contrastTestImage.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        RescaleOp op = new RescaleOp(1.2f, 0, null);
//        bufferedImage = op.filter(bufferedImage, bufferedImage);
//
//        File outputfile = new File("src/main/resources/tesseract/contrastTestImagesavedBrighter.jpg");
//        try {
//            ImageIO.write(bufferedImage, "jpg", outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }