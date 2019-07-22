package de.academyident.ident.controller;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class TesseractController {
    public static void main(String[] args) throws Exception {
        String inputFilePath = "src\\main\\resources\\tesseract\\Muster_des_Personalausweises_RS.jpg";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\tesseract");
        tesseract.setLanguage("deu");
        String fulltext = tesseract.doOCR(new File(inputFilePath));
        System.out.println(fulltext);
    }
}
