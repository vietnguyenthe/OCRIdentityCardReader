package de.academyident.ident.controller;

import de.academyident.ident.model.Personendokument;
import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.ocr.ResultInterpreter;
import de.academyident.ident.ocr.SubbildErsteller;
import de.academyident.ident.ocr.TesseractOCR;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

@Controller
@SessionAttributes("neueDokumentDaten"  )
public class TesseractController {

    private static final String fileSeparator = System.getProperty("file.separator");
    private Path uploadFolder;


    private String fileName;
    private String fileName2;

    private String frontImagePath;
    private String backImagePath;

    @PostConstruct
    public void init() {
        String path = System.getProperty("user.home") + fileSeparator + "uploads";
        uploadFolder = Paths.get(path);
        try {
            Files.createDirectories(uploadFolder);
        } catch (IOException e) {
            throw new RuntimeException("Could not create uploads directory.", e);
        }
    }

    @PostMapping(value = "/fileUpload")
    public String bearbeiteDaten(Model model,
                                 @ModelAttribute("tessImage") TesseractFile tesseractFile,
                                 @ModelAttribute("neueDokumentDaten") Personendokument dokument) throws IOException {

        MultipartFile backImage = tesseractFile.getBackImage();
        fileName = StringUtils.cleanPath(backImage.getOriginalFilename());

        InputStream inputStream = backImage.getInputStream();
        Files.copy(inputStream, uploadFolder.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("targetFileName", "readImage/" + fileName);


        MultipartFile frontImage = tesseractFile.getFrontImage();
        fileName2 = StringUtils.cleanPath(frontImage.getOriginalFilename());

        InputStream inputStream2 = frontImage.getInputStream();
        Files.copy(inputStream2, uploadFolder.resolve(fileName2), StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("targetFileName2", "readImage/" + fileName2);

        frontImagePath = uploadFolder + fileSeparator + fileName2;
        backImagePath = uploadFolder + fileSeparator + fileName;

        // Create subImages of the original upload for OCR

        SubbildErsteller subbildErsteller = new SubbildErsteller();

        subbildErsteller.erstelleMaschinenlesbareZone(backImagePath);

        String maschinenlesbareZone = TesseractOCR.leseTextaus(
                new File("src\\main\\resources\\static\\img\\maschinenLesbareZone.jpg"));

        subbildErsteller.erstelleAdresse(backImagePath);

        String adresse = TesseractOCR.leseTextaus(
                new File("src\\main\\resources\\static\\img\\adresse.jpg"));

        subbildErsteller.erstelleGeburtsort(frontImagePath);

        String geburtsort = TesseractOCR.leseTextaus(
                new File("src\\main\\resources\\static\\img\\geburtsort.jpg"));


        ResultInterpreter resultInterpreter = new ResultInterpreter(maschinenlesbareZone, adresse, geburtsort);

        HashMap<String, String> ergebnisMap = resultInterpreter.getResultMap();

        ocrModelMapping(dokument, ergebnisMap);

        List<String> dateien = new ArrayList<>(Arrays.asList("src\\main\\resources\\static\\img\\adresse.jpg",
                                                             "src\\main\\resources\\static\\img\\geburtsort.jpg",
                                                             "src\\main\\resources\\static\\img\\maschinenLesbareZone.jpg"));
//        LokaleBilddateien.loeschen(dateien);

        return "pruefung";
    }

    @GetMapping("/readImage/{imageName}")
    @ResponseBody
    public byte[] readImage(@PathVariable(value = "imageName") String imageName) throws IOException {

        File file = new File(uploadFolder + fileSeparator + imageName);
        byte[] bytes = Files.readAllBytes(file.toPath());

        return bytes;
    }

    private void ocrModelMapping(@ModelAttribute("neueDokumentDaten") Personendokument dokument, HashMap<String, String> ergebnisMap) {
        dokument.setNachname(ergebnisMap.get("nachname"));
        dokument.setVorname(ergebnisMap.get("vorname"));
        dokument.setStaatsangehoerigkeit(ergebnisMap.get("herkunftsland"));
        dokument.setGeburtsDatum(ergebnisMap.get("geburtsdatum"));
        dokument.setAusweisId(ergebnisMap.get("ausweisnummer"));
        dokument.setGeburtsOrt(ergebnisMap.get("geburtsort"));
        dokument.setStadt(ergebnisMap.get("stadt"));
        dokument.setStrasse(ergebnisMap.get("strasse"));

        try {
            dokument.setHausNr(Integer.parseInt(ergebnisMap.get("hausnummer")));
            dokument.setPlz(Integer.parseInt(ergebnisMap.get("plz")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String getFrontImagePath() {
        return frontImagePath;
    }

    public String getBackImagePath() {
        return backImagePath;
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