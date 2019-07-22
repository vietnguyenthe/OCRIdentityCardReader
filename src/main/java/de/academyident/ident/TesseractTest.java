package de.academyident.ident;

import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class TesseractTest {
    public static void main(String[] args) throws Exception {
        String inputFilePath = "src\\main\\resources\\Tesseract\\Testbild_deu.png";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\Tesseract");
        tesseract.setLanguage("deu");
        String fulltext = tesseract.doOCR(new File(inputFilePath));
        System.out.println(fulltext);
    }
}
