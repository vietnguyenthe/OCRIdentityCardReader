package de.academyident.ident.controller;

import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.util.DeutscherAusweisOCR;
import de.academyident.ident.util.SaveFile;
import de.academyident.ident.util.SubbildErsteller;
import de.academyident.ident.util.TesseractIdent;
import net.sourceforge.tess4j.Tesseract;
import org.bytedeco.opencv.presets.opencv_core;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
public class TesseractController {


    @PostMapping(value = "/fileUpload")
    public String bearbeiteDaten(Model model,
                                 @ModelAttribute("tessImage") TesseractFile tesseractFile){


        SaveFile.saveFileOnDisk(tesseractFile, "\\Perso_Back.jpg");

        SubbildErsteller subbildErsteller = new SubbildErsteller();

        subbildErsteller.erstelleMaschinenlesbareZone("src\\main\\resources\\tesseract\\Perso_Back.jpg");

        String fulltext = TesseractIdent.leseTextaus(new File("src\\main\\resources\\tesseract\\maschinenLesbareZone.jpg"));

        DeutscherAusweisOCR deutscherAusweisOCR = new DeutscherAusweisOCR(fulltext);

        HashMap<String, String> ergebnisMap = deutscherAusweisOCR.getResultMap();

        System.out.println(ergebnisMap.toString());


        return "tessPruefung";
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