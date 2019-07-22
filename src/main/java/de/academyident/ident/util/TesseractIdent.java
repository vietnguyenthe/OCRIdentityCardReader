package de.academyident.ident.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;

public class TesseractIdent {

    public static String leseTextaus(File file) {

        Tesseract tesseract = new Tesseract();
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
