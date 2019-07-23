package de.academyident.ident.TesterKlassen;

import de.academyident.ident.util.DeutscherAusweisOCR;
import net.sourceforge.tess4j.OCRResult;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TesseractTest {

    public static void main(String[] args) throws Exception {

        String file = "src/main/resources/tesseract/adresse.jpg";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\tesseract");

//        BufferedImage bufferedImage = null;
//        try {
//            bufferedImage = ImageIO.read(new File("src/main/resources/tesseract/Perso_Back_Zuschnitt.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String fulltext = null;

        fulltext = tesseract.doOCR(new File(file));

        System.out.println(fulltext);

//        DeutscherAusweisOCR deutscherAusweisOCR = new DeutscherAusweisOCR(fulltext);
//        HashMap<String, String> ergebnisMap = deutscherAusweisOCR.getResultMap();
//        System.out.println(ergebnisMap.toString());

    }


}
