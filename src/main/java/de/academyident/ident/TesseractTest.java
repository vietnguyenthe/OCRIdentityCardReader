package de.academyident.ident;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TesseractTest {

    public static void main(String[] args) throws Exception{

        String file = "src/main/resources/tesseract/contrastTestImagesavedBrighter.jpg";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\tesseract");

        String fulltext = null;

            fulltext = tesseract.doOCR(new File(file));

        System.out.println(fulltext);
    }
}
