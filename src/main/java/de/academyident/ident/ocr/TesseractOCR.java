package de.academyident.ident.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TesseractOCR {

    public static String leseTextaus(File file) {

        net.sourceforge.tess4j.Tesseract tesseract = new net.sourceforge.tess4j.Tesseract();
        tesseract.setDatapath("src\\main\\resources\\tesseract");


        String fulltext = null;
        try {
            fulltext = tesseract.doOCR(file);
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        return fulltext;
    }
}
